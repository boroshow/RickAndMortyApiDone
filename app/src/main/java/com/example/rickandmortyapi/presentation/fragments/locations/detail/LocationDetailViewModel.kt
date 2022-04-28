package com.example.rickandmortyapi.presentation.fragments.locations.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.usecase.GetLocationDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor (private val useCase: GetLocationDetailUseCase) : ViewModel() {

    private val _getLocations = MutableStateFlow<LocationEntity?>(null)
    val getLocations: Flow<LocationEntity?> get() = _getLocations

    fun fetchLocationDetail(id :Int) {
        viewModelScope.launch {
            useCase.invoke(id)
                .onStart {
                }
                .catch { exception ->
                }
                .collect { result ->
                    _getLocations.value = result
                }
        }
    }


}