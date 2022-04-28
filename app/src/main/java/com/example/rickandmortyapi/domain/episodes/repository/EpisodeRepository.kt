package com.example.rickandmortyapi.domain.episodes.repository

import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodes(): Flow<UiState<List<EpisodeEntity>>>
}