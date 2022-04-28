package com.example.rickandmortyapi.data.episodes

import com.example.rickandmortyapi.data.episodes.remote.EpisodesApi
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeDetailRepository
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(private val api: EpisodesApi) :
    EpisodeRepository, EpisodeDetailRepository {

    override fun getEpisodes(): Flow<List<EpisodeEntity>> {
            return flow {
                val response = api.getEpisodes()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.results?.let { emit(it) }
                }
            }
    }

    override fun getEpisodeDetail(episdoeid: Int): Flow<EpisodeEntity> {
        return flow {
            api.geEpisodeDetail(episdoeid)
        }
    }
}

//override fun getEpisodeDetail(episdoeid: Int) = doRequest(this::setupEpisodeSuccess) {
//    api.geEpisodeDetail(episdoeid)
//}
//
//private fun setupEpisodeSuccess(episode: EpisodeEntity) {
//    episode.id
//}
