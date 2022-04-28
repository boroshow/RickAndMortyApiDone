package com.example.rickandmortyapi.data.locations.remote

import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.entity.LocationsEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationsApi {

    @GET("location")
    suspend fun getLocations(): Response<LocationsEntity>

    @GET("location/{id}")
    suspend fun getLocationDetail(
        @Path("id") id: Int
    ): Response<LocationEntity>


}