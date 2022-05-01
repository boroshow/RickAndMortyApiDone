package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapi.databinding.FragmentEpisodeDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<FragmentEpisodeDetailBinding>() {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun bind(): FragmentEpisodeDetailBinding {
        return FragmentEpisodeDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
        observeEpisodeDetail()
    }

    override fun setupUI() {
    }

    private fun observeEpisodeDetail() {
        viewModel.fetchEpisodeDetail(args.id)
        lifecycleScope.launchWhenCreated {
            viewModel.getEpisodeDetail.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                        binding.progress.isVisible = true
                        Log.e("loading", "handleLocation: ")
                    }
                    is UiState.Success -> {
                        binding.progress.isVisible = false
                        binding.tvEpisodeNameDetail.text = it.data.name
                        binding.tvEpisodeDateDetail.text = it.data.airDate
                    }
                    is UiState.Error -> {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                    }
                }


            }
        }
    }

}