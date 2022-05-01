package com.example.rickandmortyapi.presentation.fragments.characters.detail

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortyapi.databinding.FragmentCharactersDetailBinding
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersDetailFragment : BaseFragment<FragmentCharactersDetailBinding>() {

    private val viewModel: CharactersDetailViewModel by viewModels()
    private val args: CharactersDetailFragmentArgs by navArgs()

    override fun bind(): FragmentCharactersDetailBinding {
        return FragmentCharactersDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {
    }

    override fun setupObservers() {
        viewModel.fetchCharacterDetail(args.id)
        observeCharacterDetail()
    }

    override fun setupUI() {
    }

    private fun observeCharacterDetail() {
        lifecycleScope.launchWhenCreated {
            viewModel.getCharacterDetail.collectLatest {
                when (it) {
                    is UiState.Loading -> {
                        binding.progress.isVisible = true
                        Log.e("loading", "handleLocation: ")
                    }
                    is UiState.Success -> {
                        binding.progress.isVisible = false
                        binding.tvCharacterNameDetail.text = it.data.name
                        binding.tvCharacterTypeDetail.text = it.data.gender
                        binding.tvCharacterSpeciesDetail.text = it.data.species
                        binding.tvCharacterStatusDetail.text = it.data.status
                        binding.imageCharacterDetail.load(it.data.image)
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