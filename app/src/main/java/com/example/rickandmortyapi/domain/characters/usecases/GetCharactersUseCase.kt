package com.example.rickandmortyapi.domain.characters.usecases

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    operator fun invoke(
        name: String?,
        status: String?,
        gender: String?,
    ): Flow<List<CharacterEntity>> = repository.getCharacters(name, status,gender)
}