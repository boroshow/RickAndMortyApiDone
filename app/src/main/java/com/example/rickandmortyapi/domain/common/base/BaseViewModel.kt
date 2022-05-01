package com.example.rickandmortyapi.domain.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> stateData(
        state: MutableStateFlow<UiState<T>>,
        request: () -> Flow<BaseResult<T, String>>,
    ) {
        viewModelScope.launch {
            request().collect {
                when (it) {
                    is BaseResult.Loading<*> -> {
                        state.value = UiState.Loading()
                    }
                    is BaseResult.Error -> {
                        state.value = UiState.Error(it.rawResponse)
                    }
                    is BaseResult.Success -> it.data?.let { data ->
                        state.value = UiState.Success(data)
                    }
                }
            }
        }
    }
}