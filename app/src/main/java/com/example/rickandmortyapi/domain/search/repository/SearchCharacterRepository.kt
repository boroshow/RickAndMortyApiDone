package com.example.rickandmortyapi.domain.search.repository

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface SearchCharacterRepository {
    fun getCharacterSearch(name: String): Flow<BaseResult<List<CharacterEntity>,String>>
}