package com.example.rickandmortyapi.data.characters.remote

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.characters.entity.CharactersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersEntity>


    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id: String
    ): Response<CharacterEntity>


}