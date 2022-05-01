package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.usecase.GetEpisodesDetailUseCase
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(private val getEpisodesDetailUseCase: GetEpisodesDetailUseCase) :
    BaseViewModel() {

    private val _getEpisodeDetail = MutableStateFlow<UiState<EpisodeEntity>>(UiState.Loading())
    val getEpisodeDetail: Flow<UiState<EpisodeEntity>> = _getEpisodeDetail

    fun fetchEpisodeDetail(id: Int) {
        stateData(_getEpisodeDetail) {
            getEpisodesDetailUseCase(id)
        }
    }

}