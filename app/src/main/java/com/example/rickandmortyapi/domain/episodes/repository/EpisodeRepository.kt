package com.example.rickandmortyapi.domain.episodes.repository

import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodes() : Flow<List<EpisodeEntity>>
}