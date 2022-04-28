package com.example.rickandmortyapi.presentation.fragments.locations

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.databinding.FragmentLocationsBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding>() {

    private val viewModel: LocationsViewModel by viewModels()

    private val adapter: LocationsAdapter by lazy {
        LocationsAdapter()
    }

    override fun bind(): FragmentLocationsBinding {
        return FragmentLocationsBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
        adapter.setOnClick(object : LocationsAdapter.OnClick {
            override fun onClicked(position: LocationEntity) {
                findNavController().navigate(
                    LocationsFragmentDirections.actionLocationsFragmentToLocationsDetailFragment(position.planetId)
                )
            }
        })
    }

    override fun setupObservers() {
        observeCharacters()
        observeState()
    }

    override fun setupUI() {
        binding.rvLocations.apply {
            adapter = this@LocationsFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCharacters() {
        lifecycleScope.launch {
            viewModel.getLocations.collectLatest {
                Log.e("TAG", it.toString())
                adapter.submitList(it)
            }
        }
    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: LocationsViewModel.LocationsFragmentState) {
        when (state) {
            is LocationsViewModel.LocationsFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is LocationsViewModel.LocationsFragmentState.Init -> Unit
            is LocationsViewModel.LocationsFragmentState.IsLoading -> useLoading(state.isLoading)
        }
    }

    private fun useLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }
}