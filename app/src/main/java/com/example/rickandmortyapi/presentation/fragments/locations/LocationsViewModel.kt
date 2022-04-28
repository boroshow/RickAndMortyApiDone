package com.example.rickandmortyapi.presentation.fragments.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.locations.usecase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(private val getLocationsUseCase: GetLocationsUseCase) :
    ViewModel() {

    private val _getLocations = MutableStateFlow<List<LocationEntity>>(mutableListOf())
    val getLocations: Flow<List<LocationEntity>> get() = _getLocations

    private val _state =
        MutableStateFlow<LocationsFragmentState>(LocationsFragmentState.Init)
    val state: StateFlow<LocationsFragmentState> get() = _state


    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            getLocationsUseCase.invoke()
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message)
                }
                .collect { result ->
                    hideLoading()
                    _getLocations.value = result
                }
        }
    }


    private fun showToast(message: String?) {
        _state.value = LocationsFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = LocationsFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = LocationsFragmentState.IsLoading(true)
    }

    sealed class LocationsFragmentState {
        object Init : LocationsFragmentState()
        data class IsLoading(val isLoading: Boolean) : LocationsFragmentState()
        data class ShowToast(val message: String?) : LocationsFragmentState()
    }

}
