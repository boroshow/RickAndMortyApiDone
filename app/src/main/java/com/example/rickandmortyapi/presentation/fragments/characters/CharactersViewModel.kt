package com.example.rickandmortyapi.presentation.fragments.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {

    private val _getCharacters = MutableStateFlow<List<CharacterEntity>>(mutableListOf())
    val getCharacters: Flow<List<CharacterEntity>> get() = _getCharacters

    private val _state =
        MutableStateFlow<CharactersFragmentState>(CharactersFragmentState.Init)
    val state: StateFlow<CharactersFragmentState> get() = _state


    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            getCharactersUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message)
                }
                .collect { result ->
                    hideLoading()
                    _getCharacters.value = result
                }
        }
    }


    private fun showToast(message: String?) {
        _state.value = CharactersFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = CharactersFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = CharactersFragmentState.IsLoading(true)
    }

    sealed class CharactersFragmentState {
        object Init : CharactersFragmentState()
        data class IsLoading(val isLoading: Boolean) : CharactersFragmentState()
        data class ShowToast(val message: String?) : CharactersFragmentState()
    }

}