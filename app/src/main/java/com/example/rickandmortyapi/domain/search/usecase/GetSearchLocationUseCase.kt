package com.example.rickandmortyapi.domain.search.usecase

import com.example.rickandmortyapi.domain.search.repository.SearchLocationRepository
import javax.inject.Inject

class GetSearchLocationUseCase @Inject constructor(private val repository: SearchLocationRepository) {
    fun invoke(name: String) = repository.getLocationSearch(name)
}