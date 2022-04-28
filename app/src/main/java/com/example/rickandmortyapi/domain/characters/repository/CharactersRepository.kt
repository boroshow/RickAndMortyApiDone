package com.example.rickandmortyapi.domain.characters.repository

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<List<CharacterEntity>>
}