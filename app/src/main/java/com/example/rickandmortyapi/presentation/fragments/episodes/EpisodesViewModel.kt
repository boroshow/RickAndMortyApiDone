package com.example.rickandmortyapi.presentation.fragments.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.usecase.GetEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(private val getEpisodesUseCase: GetEpisodesUseCase) :
    ViewModel() {

    private val _getEpisodes = MutableStateFlow<List<EpisodeEntity>>(mutableListOf())
    val getEpisodes: Flow<List<EpisodeEntity>> get() = _getEpisodes

    private val _state =
        MutableStateFlow<EpisdodesFragmentState>(EpisdodesFragmentState.Init)
    val state: StateFlow<EpisdodesFragmentState> get() = _state

    fun fetchEpisodes(name: String?, episode: String?) {
        viewModelScope.launch {
            getEpisodesUseCase.invoke(name, episode)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message)
                }
                .collect { result ->
                    hideLoading()
                    _getEpisodes.value = result
                }
        }
    }


    private fun showToast(message: String?) {
        _state.value = EpisdodesFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = EpisdodesFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = EpisdodesFragmentState.IsLoading(true)
    }

    sealed class EpisdodesFragmentState {
        object Init : EpisdodesFragmentState()
        data class IsLoading(val isLoading: Boolean) : EpisdodesFragmentState()
        data class ShowToast(val message: String?) : EpisdodesFragmentState()
    }

}