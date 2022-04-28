package com.example.rickandmortyapi.presentation.fragments.characters.detail

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyapi.databinding.FragmentCharactersDetailBinding
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseFragment
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CharactersDetailFragment : BaseFragment<FragmentCharactersDetailBinding>() {

    private val viewModel: CharactersDetailViewModel by viewModels()


    override fun bind(): FragmentCharactersDetailBinding {
        return FragmentCharactersDetailBinding.inflate(layoutInflater)
    }

    override fun setupListeners() {

    }

    override fun setupObservers() {
        observeCharacterDetail()
        observeState()
    }

    override fun setupUI() {
        val it : UiState<CharacterEntity>
        UiState.Success{

        }
    }

    private fun setup(it : UiState<CharacterEntity>){

    }

    private fun observeState() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun observeCharacterDetail() {
//        viewModel.fetchCharacterDetail()
    }

    private fun handleState(state: CharactersDetailViewModel.CharactersDetailFragmentState) {
        when (state) {
            is CharactersDetailViewModel.CharactersDetailFragmentState.ShowToast -> Toast.makeText(
                requireContext(),
                state.message,
                Toast.LENGTH_SHORT
            ).show()
            is CharactersDetailViewModel.CharactersDetailFragmentState.Init -> Unit
            is CharactersDetailViewModel.CharactersDetailFragmentState.IsLoading -> useLoading(state.isLoading)
        }
    }

    private fun useLoading(loading: Boolean) {
        binding.progress.isVisible = loading
    }


}