package com.example.rickandmortyapi.data.episodes.remote

import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.entity.EpisodesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

    @GET("episode")
    suspend fun getEpisodes(): Response<EpisodesEntity>

    @GET("episode/{id}")
    suspend fun geEpisodeDetail(
        @Path("id") id: Int
    ): EpisodeEntity

}