package com.example.rickandmortyapi.domain.locations.repository

import com.example.rickandmortyapi.data.common.Resource
import com.example.rickandmortyapi.domain.common.base.BaseResult
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationDetailRepository {
    fun getLocationDetail(locationId: Int): Flow<BaseResult<LocationEntity, String>>
}