package com.example.rickandmortyapi.domain.search.usecase

import com.example.rickandmortyapi.domain.search.repository.SearchCharacterRepository
import javax.inject.Inject

class GetSearchCharacterUseCase @Inject constructor(private val repository: SearchCharacterRepository) {
    fun invoke(name: String) = repository.getCharacterSearch(name)
}