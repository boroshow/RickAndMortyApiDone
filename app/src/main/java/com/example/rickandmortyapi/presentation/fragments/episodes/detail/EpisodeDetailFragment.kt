package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.presentation.state.UiState

class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel,FragmentEpisodeDetailBinding>(R.layout.fragment_episode_detail) {

    override val viewModel: EpisodeDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)

    override fun initialize() {
    }

    override fun setupRequests() {
        fetchFoo()
    }

    private fun fetchFoo() {
        viewModel.fetchFoo()
    }

    override fun setupSubscribes() {
        subscribeToEpisodeDetail()
    }

    private fun subscribeToEpisodeDetail() {
        viewModel.episodeDetailState.collectUIState {
            binding.progress.isVisible = it is UiState.Loading
            when (it) {
                is UiState.Idle -> {
                }
                is UiState.Loading -> {
                }
                is UiState.Error -> {
                    it.error
                }
                is UiState.Success -> {
                    it.data
                }
            }
        }

    }
}