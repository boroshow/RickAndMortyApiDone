package com.example.rickandmortyapi.presentation.fragments.locations.detail

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapi.databinding.FragmentLocationsDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class LocationsDetailFragment : BaseFragment<FragmentLocationsDetailBinding>() {

    private val args: LocationsDetailFragmentArgs by navArgs()

    private val viewModel: LocationDetailViewModel by viewModels()

    override fun bind(): FragmentLocationsDetailBinding {
        return FragmentLocationsDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
        viewModel.fetchLocationDetail(args.id)
        lifecycleScope.launchWhenCreated {
            viewModel.getLocations.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                        binding.progress.isVisible = true
                        Log.e("loading", "handleLocation: ")
                    }
                    is UiState.Success -> {
                        binding.progress.isVisible = false
                        binding.tvLocationNameDetail.text = it.data.name
                        binding.tvLocationTypeDetail.text = it.data.type
                        binding.tvLocationDimensionDetail.text = it.data.dimension
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

    override fun setupUI() {

    }

}