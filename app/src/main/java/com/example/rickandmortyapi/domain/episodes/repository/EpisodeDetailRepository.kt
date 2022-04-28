package com.example.rickandmortyapi.domain.episodes.repository

import com.example.rickandmortyapi.domain.Either
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

interface EpisodeDetailRepository {
    fun getEpisodeDetail(episdoeid : Int) : Flow<Either<String, EpisodeEntity>>
}