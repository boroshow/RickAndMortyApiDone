package com.example.rickandmortyapi.domain.characters.repository

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    fun getCharacterDetail(characterId: String?): Flow<UiState<CharacterEntity>>
}