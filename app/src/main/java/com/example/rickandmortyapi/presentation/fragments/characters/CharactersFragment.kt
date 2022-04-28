package com.example.rickandmortyapi.presentation.fragments.characters

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun bind(): FragmentCharactersBinding {
        return FragmentCharactersBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        adapter.setOnClick(object : CharactersAdapter.OnClick{
            override fun onClicked(position: CharacterEntity) {
                findNavController().navigate(
                    CharactersFragmentDirections.actionCharactersFragmentToCharactersDetailFragment(position.characterId.toString())
                )
            }
        })
    }

    override fun setupObservers() {
        observeCharacters()
        observeState()
    }

    override fun setupUI() {
        binding.rvCharacters.apply {
            adapter = this@CharactersFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCharacters() {
        lifecycleScope.launch {
            viewModel.getCharacters.collectLatest {
                Log.e("TAG", it.toString())
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