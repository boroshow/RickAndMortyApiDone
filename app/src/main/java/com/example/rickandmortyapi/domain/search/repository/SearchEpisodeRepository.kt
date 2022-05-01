package com.example.rickandmortyapi.domain.search.repository

import com.example.rickandmortyapi.domain.common.base.BaseResult
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface SearchEpisodeRepository {
    fun getEpisodeSearch(name: String): Flow<BaseResult<List<EpisodeEntity>,String>>
}