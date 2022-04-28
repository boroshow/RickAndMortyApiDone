package com.example.rickandmortyapi.data.episodes

import com.example.rickandmortyapi.data.episodes.remote.EpisodesApi
import com.example.rickandmortyapi.domain.common.base.BaseRepository
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeDetailRepository
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeRepository
import com.example.rickandmortyapi.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(private val api: EpisodesApi) : BaseRepository(),
    EpisodeRepository, EpisodeDetailRepository {

    override fun getEpisodes() = doRequest (this::setupEpisodeSuccess) {
        api.getEpisodes()
    }

    override fun getEpisodeDetail(episdoeid: Int) = doRequest(this::setupEpisodeSuccess) {
        api.geEpisodeDetail(episdoeid)
    }

    private fun setupEpisodeSuccess(episode: EpisodeEntity) {
        episode.id
    }
}