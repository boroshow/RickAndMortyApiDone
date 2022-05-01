package com.example.rickandmortyapi.presentation.fragments.locations.detail

import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.usecase.GetLocationDetailUseCase
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(private val getLocationDetailUseCase: GetLocationDetailUseCase) :
    BaseViewModel() {

    private val _getLocations = MutableStateFlow<UiState<LocationEntity>>(UiState.Loading())
    val getLocations: Flow<UiState<LocationEntity>> = _getLocations

    fun fetchLocationDetail(id: Int) {
        stateData(_getLocations) {
            getLocationDetailUseCase(id)
        }
    }


}