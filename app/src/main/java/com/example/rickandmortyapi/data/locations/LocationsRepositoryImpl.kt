package com.example.rickandmortyapi.data.locations

import com.example.rickandmortyapi.data.locations.remote.LocationsApi
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.repository.LocationDetailRepository
import com.example.rickandmortyapi.domain.locations.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(private val api: LocationsApi) :
    LocationRepository,LocationDetailRepository {

    override fun getLocations(): Flow<List<LocationEntity>> {
        return flow {
            val response = api.getLocations()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

    override fun getLocationDetail(id : Int): Flow<LocationEntity> {
        return flow {
            api.getLocationDetail(id)
        }
    }

}