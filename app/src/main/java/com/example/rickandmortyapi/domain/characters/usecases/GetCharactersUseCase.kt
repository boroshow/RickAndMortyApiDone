package com.example.rickandmortyapi.domain.characters.usecases

import com.example.rickandmortyapi.domain.characters.repository.CharactersRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    fun invoke() = repository.getCharacters()
}