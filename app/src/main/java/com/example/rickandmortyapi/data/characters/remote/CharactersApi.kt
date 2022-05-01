package com.example.rickandmortyapi.data.characters.remote

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.entity.CharactersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("gender") gender: String?,
    ): Response<CharactersEntity>

    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: Int,
    ): CharacterEntity

}