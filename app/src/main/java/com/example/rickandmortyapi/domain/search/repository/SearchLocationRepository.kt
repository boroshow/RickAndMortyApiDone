package com.example.rickandmortyapi.domain.search.repository

import com.example.rickandmortyapi.domain.common.base.BaseResult
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface SearchLocationRepository {
    fun getLocationSearch(name: String): Flow<BaseResult<List<LocationEntity>,String>>
}