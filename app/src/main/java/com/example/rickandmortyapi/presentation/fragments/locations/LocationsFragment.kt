package com.example.rickandmortyapi.presentation.fragments.locations

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
        adapterOnclick()
    }

    private fun adapterOnclick() {
        adapter.setOnClick(object : LocationsAdapter.OnClick {
            override fun onClicked(position: LocationEntity) {
                findNavController().navigate(
                    LocationsFragmentDirections.actionLocationsFragmentToLocationsDetailFragment(
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
        // initDialog()
    }

    private fun etLogic() {
        binding.etSearchLocations.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.fetchLocations(binding.etSearchLocations.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.fetchLocations(binding.etSearchLocations.text.toString())
            }
        })
    }


    private fun initAdapter() {
        binding.rvLocations.apply {
            adapter = this@LocationsFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCharacters() {
        viewModel.fetchLocations(null)
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