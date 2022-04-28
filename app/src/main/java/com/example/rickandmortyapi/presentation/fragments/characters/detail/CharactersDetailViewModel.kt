package com.example.rickandmortyapi.presentation.fragments.characters.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.usecases.GetCharacterDetailUseCase
import com.example.rickandmortyapi.presentation.fragments.characters.CharactersViewModel
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(private val getCharacterDetailUseCase: GetCharacterDetailUseCase) :
    ViewModel() {

    private val _getCharacterDetail = MutableStateFlow<UiState<CharacterEntity>>(UiState.Loading())

    private val _state =
        MutableStateFlow<CharactersDetailFragmentState>(CharactersDetailFragmentState.Init)
    val state: StateFlow<CharactersDetailFragmentState> get() = _state

    fun fetchCharacterDetail(characterId : String?) {
        viewModelScope.launch {
            getCharacterDetailUseCase.invoke(characterId)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message)
                }
                .collect { result ->
                    hideLoading()
                    _getCharacterDetail.value = result
                }
        }
    }
    private fun showToast(message: String?) {
        _state.value = CharactersDetailFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = CharactersDetailFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = CharactersDetailFragmentState.IsLoading(true)
    }

    sealed class CharactersDetailFragmentState {
        object Init : CharactersDetailFragmentState()
        data class IsLoading(val isLoading: Boolean) : CharactersDetailFragmentState()
        data class ShowToast(val message: String?) : CharactersDetailFragmentState()
    }

}

