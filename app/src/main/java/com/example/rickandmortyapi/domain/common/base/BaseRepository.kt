package com.example.rickandmortyapi.domain.common.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doExecution(execution: suspend () -> T): Flow<BaseResult<T, String>> {
        return flow {
            emit(BaseResult.Loading(null))
            try {
                emit(BaseResult.Success(data = execution()))
            } catch (exception: Exception) {
                emit(BaseResult.Error(rawResponse = exception.localizedMessage ?: "Error data"))
            }
        }
    }

}