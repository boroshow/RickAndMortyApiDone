package com.example.rickandmortyapi.domain.episodes.usecase

import com.example.rickandmortyapi.domain.episodes.repository.EpisodeDetailRepository
import javax.inject.Inject

class GetEpisodesDetailUseCase @Inject constructor(private val repository: EpisodeDetailRepository) {
    operator fun invoke(id: Int) = repository.getEpisodeDetail(id)
}