package com.example.rickandmortyapi.domain.locations.usecase

import com.example.rickandmortyapi.domain.locations.repository.LocationDetailRepository
import javax.inject.Inject

class GetLocationDetailUseCase @Inject constructor(private val repository: LocationDetailRepository) {
    fun invoke(id:Int) = repository.getLocationDetail(id)
}