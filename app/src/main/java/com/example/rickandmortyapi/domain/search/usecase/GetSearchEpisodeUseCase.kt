package com.example.rickandmortyapi.domain.search.usecase

import com.example.rickandmortyapi.domain.search.repository.SearchEpisodeRepository
import javax.inject.Inject

class GetSearchEpisodeUseCase @Inject constructor(private val repository: SearchEpisodeRepository) {
    fun invoke(name: String) = repository.getEpisodeSearch(name)
}