package com.example.rickandmortyapi.data.search

import com.example.rickandmortyapi.data.search.remote.SearchApi
import com.example.rickandmortyapi.domain.common.base.BaseRepository
import com.example.rickandmortyapi.domain.search.repository.SearchCharacterRepository
import com.example.rickandmortyapi.domain.search.repository.SearchEpisodeRepository
import com.example.rickandmortyapi.domain.search.repository.SearchLocationRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: SearchApi) : BaseRepository(),
    SearchCharacterRepository, SearchEpisodeRepository, SearchLocationRepository {

    override fun getCharacterSearch(name: String) = doExecution {
        api.getCharacter(name).results.map {
            it
        }
    }

    override fun getEpisodeSearch(name: String) = doExecution {
        api.getEpisode(name).results.map {
            it
        }
    }

    override fun getLocationSearch(name: String) = doExecution {
        api.getLocation(name).results.map {
            it
        }
    }

}

