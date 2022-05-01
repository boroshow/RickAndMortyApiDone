package com.example.rickandmortyapi.data.locations

import com.example.rickandmortyapi.data.locations.remote.LocationsApi
import com.example.rickandmortyapi.domain.common.base.BaseRepository
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.repository.LocationDetailRepository
import com.example.rickandmortyapi.domain.locations.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(private val api: LocationsApi) : BaseRepository(),
    LocationRepository, LocationDetailRepository {

    override fun getLocations(name: String?): Flow<List<LocationEntity>> {
        return flow {
            val response = api.getLocations(name)
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

    override fun getLocationDetail(locationId: Int) = doExecution {
        api.getLocationDetail(locationId)
    }

}