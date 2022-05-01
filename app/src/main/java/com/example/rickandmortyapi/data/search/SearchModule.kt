package com.example.rickandmortyapi.data.search

import com.example.rickandmortyapi.data.common.module.NetworkModule
import com.example.rickandmortyapi.data.search.remote.SearchApi
import com.example.rickandmortyapi.domain.search.repository.SearchCharacterRepository
import com.example.rickandmortyapi.domain.search.repository.SearchEpisodeRepository
import com.example.rickandmortyapi.domain.search.repository.SearchLocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCharactersSearchRepository(searchApi: SearchApi): SearchCharacterRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun providesEpisodesSearchRepository(searchApi: SearchApi): SearchEpisodeRepository {
        return SearchRepositoryImpl(searchApi)
    }

    @Singleton
    @Provides
    fun providesLocationsSearchRepository(searchApi: SearchApi): SearchLocationRepository {
        return SearchRepositoryImpl(searchApi)
    }

}