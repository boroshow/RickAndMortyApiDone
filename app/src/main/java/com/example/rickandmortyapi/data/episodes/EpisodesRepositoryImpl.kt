package com.example.rickandmortyapi.data.episodes

import com.example.rickandmortyapi.data.episodes.remote.EpisodesApi
import com.example.rickandmortyapi.domain.common.base.BaseRepository
import com.example.rickandmortyapi.domain.episodes.entity.EpisodeEntity
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeDetailRepository
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EpisodesRepositoryImpl @Inject constructor(private val api: EpisodesApi) : BaseRepository(),
    EpisodeRepository, EpisodeDetailRepository {

    override fun getEpisodes(name: String?, episode: String?): Flow<List<EpisodeEntity>> {
        return flow {
            val response = api.getEpisodes(name, episode)
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

    override fun getEpisodeDetail(episodeId: Int) = doExecution {
        api.geEpisodeDetail(episodeId)
    }

}
