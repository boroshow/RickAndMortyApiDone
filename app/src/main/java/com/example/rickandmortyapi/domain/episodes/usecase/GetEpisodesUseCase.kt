package com.example.rickandmortyapi.domain.episodes.usecase

import com.example.rickandmortyapi.domain.episodes.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(private val repository: EpisodeRepository) {
    fun invoke() = repository.getEpisodes()
}