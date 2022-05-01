package com.example.rickandmortyapi.presentation.fragments.search

import com.example.rickandmortyapi.domain.characters.entity.CharacterEntity
import com.example.rickandmortyapi.domain.common.base.BaseViewModel
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.locations.entity.LocationEntity
import com.example.rickandmortyapi.domain.search.usecase.GetSearchCharacterUseCase
import com.example.rickandmortyapi.domain.search.usecase.GetSearchEpisodeUseCase
import com.example.rickandmortyapi.domain.search.usecase.GetSearchLocationUseCase
import com.example.rickandmortyapi.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCharactersUseCase: GetSearchCharacterUseCase,
    private val getLocationsUseCase: GetSearchLocationUseCase,
    private val getEpisodesUseCase: GetSearchEpisodeUseCase,
) : BaseViewModel() {

    private val _fetchCharacterSearch =
        MutableStateFlow<UiState<List<CharacterEntity>>>(UiState.Loading())
    val fetchCharacterSearch: StateFlow<UiState<List<CharacterEntity>>>
        get() =
            _fetchCharacterSearch

    fun fetchCharacter(name: String) {
        stateData(_fetchCharacterSearch) {
            getCharactersUseCase.invoke(name)
        }
    }

    private val _fetchLocationSearch =
        MutableStateFlow<UiState<List<LocationEntity>>>(UiState.Loading())
    val fetchLocationSearch: StateFlow<UiState<List<LocationEntity>>> get() = _fetchLocationSearch

    fun fetchLocations(name: String) {
        stateData(_fetchLocationSearch) {
            getLocationsUseCase.invoke(name)
        }
    }

    private val _fetchEpisodeSearch =
        MutableStateFlow<UiState<List<EpisodeEntity>>>(UiState.Loading())
    val fetchEpisodeSearch: StateFlow<UiState<List<EpisodeEntity>>> get() = _fetchEpisodeSearch

    fun fetchEpisodes(name: String) {
        stateData(_fetchEpisodeSearch) {
            getEpisodesUseCase.invoke(name)
        }
    }
}


