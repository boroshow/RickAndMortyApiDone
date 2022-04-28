package com.example.rickandmortyapi.presentation.fragments.locations.detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapi.databinding.FragmentLocationsDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationsDetailFragment : BaseFragment<FragmentLocationsDetailBinding>() {

    private val args : LocationsDetailFragmentArgs by navArgs()

    private val viewModel : LocationDetailViewModel by viewModels()

    override fun bind(): FragmentLocationsDetailBinding {
        return FragmentLocationsDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
        lifecycleScope.launch{
            viewModel.fetchLocationDetail(args.id)
            viewModel.getLocations.collect {
                Log.e("TAG", it?.dimension.toString() )
            }
        }
    }

    override fun setupUI() {
    }

}