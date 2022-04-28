package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.domain.episodes.usecase.GetEpisodesDetailUseCase
import com.example.rickandmortyapi.presentation.models.Episode
import com.example.rickandmortyapi.presentation.models.toUi
import kotlinx.coroutines.flow.*

class EpisodeDetailViewModel(private val useCase : GetEpisodesDetailUseCase) : BaseViewModel() {

    private val _episodeDetailState = mutableUiStateFlow<Episode>()
    val episodeDetailState = _episodeDetailState.asStateFlow()

    fun fetchFoo(episodeId:Int) {
        useCase(episodeId).collectRequest(_episodeDetailState) { it.toUi() }
    }

}