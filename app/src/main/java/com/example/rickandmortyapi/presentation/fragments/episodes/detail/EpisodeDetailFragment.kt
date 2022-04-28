package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import androidx.fragment.app.Fragment

class EpisodeDetailFragment : Fragment() {

}


//    override val viewModel: EpisodeDetailViewModel by viewModels()
//    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
//
//    override fun initialize() {
//    }
//
//    override fun setupRequests() {
//        fetchEpisodeDetail()
//    }
//
//    private fun fetchEpisodeDetail(episodeId:Int) {
//        viewModel.fetchEpisodeDetail(episodeId)
//    }
//
//    override fun setupSubscribes() {
//        subscribeToEpisodeDetail()
//    }
//
//    private fun subscribeToEpisodeDetail() {
//        viewModel.episodeDetailState.collectUIState {
//            binding.progress.isVisible = it is UiState.Loading
//            when (it) {
//                is UiState.Idle -> {
//                }
//                is UiState.Loading -> {
//                }
//                is UiState.Error -> {
//                    it.error
//                }
//                is UiState.Success -> {
//                    it.data
//                }
//            }
//        }
//
//    }
//}