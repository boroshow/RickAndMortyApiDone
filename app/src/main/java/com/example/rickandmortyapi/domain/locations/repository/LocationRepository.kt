package com.example.rickandmortyapi.domain.locations.repository

import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocations() : Flow<List<LocationEntity>>
}