package com.example.rickandmortyapi.presentation.fragments.episodes.detail

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapi.domain.episodes.usecase.GetEpisodesDetailUseCase
import com.example.rickandmortyapi.presentation.models.Episode
import kotlinx.coroutines.flow.*

//class EpisodeDetailViewModel(private val useCase : GetEpisodesDetailUseCase) : ViewModel() {
//
//    private val _episodeDetailState = mutableUiStateFlow<Episode>()
//    val episodeDetailState = _episodeDetailState.asStateFlow()
//
//    fun fetchEpisodeDetail(episodeId:Int) {
//        useCase(episodeId).collectRequest(_episodeDetailState) { it.toUi() }
//    }
//
//}