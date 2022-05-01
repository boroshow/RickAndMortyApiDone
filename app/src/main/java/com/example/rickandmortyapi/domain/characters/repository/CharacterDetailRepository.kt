package com.example.rickandmortyapi.domain.characters.repository

import com.example.rickandmortyapi.data.common.Resource
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseResult
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    fun getCharacterDetail(characterId: Int): Flow<BaseResult<CharacterEntity, String>>
}