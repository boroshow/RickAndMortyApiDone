package com.example.rickandmortyapi.presentation.fragments.characters

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentCharactersBinding
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {

    private val viewModel: CharactersViewModel by viewModels()
    private val args : CharactersFragmentArgs by navArgs()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun bind(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        adapterOnclick()
        imageClick()
    }

    private fun adapterOnclick() {
        adapter.setOnClick(object : CharactersAdapter.OnClick {
            override fun onClicked(position: CharacterEntity) {
                findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailFragment(
                        position.id)
                )
            }
        })
    }

    override fun setupObservers() {
        observeCharacters()
        observeState()
    }

    override fun setupUI() {
        etLogic()
        initAdapter()
    }

    private fun imageClick() {
        binding.imageSearch.setOnClickListener {
            findNavController().navigate(R.id.action_charactersFragment_to_characterFilterFragment)
        }
    }

    private fun etLogic() {
        binding.etSearchCharacters.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.fetchCharacters(binding.etSearchCharacters.text.toString(), null, null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.fetchCharacters(binding.etSearchCharacters.text.toString(), null, null)
            }
        })
    }

    private fun initAdapter() {
        binding.rvCharacters.apply {
            adapter = this@CharactersFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCharacters() {
        Log.e("TAG", args.liveStatus.toString() )
        viewModel.fetchCharacters(null, args.liveStatus,args.genderStatus)
        lifecycleScope.launch {
            viewModel.getCharacters.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CharactersViewModel.CharactersFragmentState) {
        when (state) {
            is CharactersViewModel.CharactersFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is CharactersViewModel.CharactersFragmentState.Init -> Unit
            is CharactersViewModel.CharactersFragmentState.IsLoading -> useLoading(state.isLoading)
        }
    }

    private fun useLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }
}