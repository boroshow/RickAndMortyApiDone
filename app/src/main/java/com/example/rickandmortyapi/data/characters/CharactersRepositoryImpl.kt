package com.example.rickandmortyapi.data.characters

import com.example.rickandmortyapi.data.characters.remote.CharactersApi
import com.example.rickandmortyapi.domain.Either
import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.repository.CharacterDetailRepository
import com.example.rickandmortyapi.domain.characters.repository.CharactersRepository
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val api: CharactersApi) :
    CharactersRepository, CharacterDetailRepository{

    override fun getCharacters(): Flow<List<CharacterEntity>> {
        return flow {
            val response = api.getCharacters()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

    override fun getCharacterDetail(characterId: String?): Flow<UiState<CharacterEntity>> {
        return flow {
            api.getCharacterDetail(characterId.toString())

        }
    }

}