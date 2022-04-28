package com.example.rickandmortyapi.data.locations.remote

import com.example.rickandmortyapi.domain.locations.entity.LocationsEntity
import retrofit2.Response
import retrofit2.http.GET

interface LocationsApi {

    @GET("location")
    suspend fun getLocations(): Response<LocationsEntity>

}