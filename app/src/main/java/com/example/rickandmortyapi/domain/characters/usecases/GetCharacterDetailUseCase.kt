package com.example.rickandmortyapi.domain.characters.usecases

import com.example.rickandmortyapi.domain.characters.repository.CharacterDetailRepository
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(private val repository: CharacterDetailRepository) {
    operator fun invoke(characterId : Int) = repository.getCharacterDetail(characterId)
}