package com.example.rickandmortyapi.domain.locations.repository

import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LocationDetailRepository {
    fun getLocationDetail(id:Int) : Flow<LocationEntity>
}