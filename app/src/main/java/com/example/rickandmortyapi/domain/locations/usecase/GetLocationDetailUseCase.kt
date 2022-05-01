package com.example.rickandmortyapi.domain.locations.usecase

import com.example.rickandmortyapi.domain.locations.repository.LocationDetailRepository
import javax.inject.Inject

class GetLocationDetailUseCase @Inject constructor(private val repository: LocationDetailRepository) {
    operator fun invoke(locationId: Int) = repository.getLocationDetail(locationId)
}