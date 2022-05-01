package com.example.rickandmortyapi.data.search.remote

import com.example.rickandmortyapi.domain.characters.entity.CharactersEntity
import com.example.rickandmortyapi.domain.episodes.entity.EpisodesEntity
import com.example.rickandmortyapi.domain.locations.entity.LocationsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("location")
    suspend fun getLocation(
        @Query("name") name: String,
    ): LocationsEntity

    @GET("episode")
    suspend fun getEpisode(
        @Query("name") name: String,
    ): EpisodesEntity

    @GET("character")
    suspend fun getCharacter(
        @Query("name") name: String,
    ): CharactersEntity

}