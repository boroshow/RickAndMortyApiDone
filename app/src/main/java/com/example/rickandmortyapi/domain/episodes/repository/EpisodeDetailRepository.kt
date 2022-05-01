package com.example.rickandmortyapi.domain.episodes.repository

import com.example.rickandmortyapi.data.common.Resource
import com.example.rickandmortyapi.domain.common.base.BaseResult
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailRepository {
    fun getEpisodeDetail(episodeId: Int): Flow<BaseResult<EpisodeEntity, String>>
}