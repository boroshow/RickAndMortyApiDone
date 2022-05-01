package com.example.rickandmortyapi.presentation.fragments.characters.detail

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.usecases.GetCharacterDetailUseCase
import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharactersDetailViewModel @Inject constructor(private val getCharacterDetailUseCase: GetCharacterDetailUseCase) :
    BaseViewModel() {

    private val _getCharacterDetail = MutableStateFlow<UiState<CharacterEntity>>(UiState.Loading())
    val getCharacterDetail: StateFlow<UiState<CharacterEntity>> = _getCharacterDetail

    fun fetchCharacterDetail(id: Int) {
        stateData(_getCharacterDetail) {
            getCharacterDetailUseCase(id)
        }
    }

}

