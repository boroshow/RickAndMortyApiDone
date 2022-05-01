package com.example.rickandmortyapi.domain.locations.usecase

import com.example.rickandmortyapi.domain.locations.repository.LocationRepository
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(private val repository: LocationRepository) {
    fun invoke(name: String?) = repository.getLocations(name)
}