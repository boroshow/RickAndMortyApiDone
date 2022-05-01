package com.example.rickandmortyapi.presentation.fragments.episodes

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentEpisodesBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding>() {

    private val viewModel: EpisodesViewModel by viewModels()
    private val args: EpisodesFragmentArgs by navArgs()
    private val adapter: EpisodesAdapter by lazy {
        EpisodesAdapter()
    }

    override fun bind(): FragmentEpisodesBinding {
        return FragmentEpisodesBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        imageClick()
        adapterOnclick()
    }

    private fun imageClick() {
        binding.imageSort.setOnClickListener {
            findNavController().navigate(R.id.action_episodesFragment_to_episodeFilterFragment)
        }
    }

    private fun adapterOnclick() {
        adapter.setOnClick(object : EpisodesAdapter.OnClick {
            override fun onClicked(position: EpisodeEntity) {
                findNavController().navigate(
                    EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(
                        position.id)
                )
            }
        })
    }

    override fun setupObservers() {
        observeState()
        observeEpisodes()
    }

    override fun setupUI() {
        etLogic()
        initAdapter()
    }

    private fun etLogic() {
        binding.etSearchEpisodes.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.fetchEpisodes(binding.etSearchEpisodes.text.toString(), null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.fetchEpisodes(binding.etSearchEpisodes.text.toString(), null)
            }
        })
    }

    private fun initAdapter() {
        binding.rvEpisodes.apply {
            adapter = this@EpisodesFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeEpisodes() {
        viewModel.fetchEpisodes(null, args.episode)
        lifecycleScope.launch {
            viewModel.getEpisodes.collectLatest {
                Log.e("TAG", it.toString())
                adapter.submitList(it)
            }
        }
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: EpisodesViewModel.EpisdodesFragmentState) {
        when (state) {
            is EpisodesViewModel.EpisdodesFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is EpisodesViewModel.EpisdodesFragmentState.Init -> Unit
            is EpisodesViewModel.EpisdodesFragmentState.IsLoading -> useLoading(state.isLoading)
        }
    }

    private fun useLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }
}