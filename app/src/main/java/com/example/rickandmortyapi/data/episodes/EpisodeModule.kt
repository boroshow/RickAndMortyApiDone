package com.example.rickandmortyapi.data.episodes

import com.example.rickandmortyapi.data.common.module.NetworkModule
import com.example.rickandmortyapi.data.episodes.remote.EpisodesApi
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeDetailRepository
import com.example.rickandmortyapi.domain.episodes.repository.EpisodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class EpisodeModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(retrofit: Retrofit): EpisodesApi {
        return retrofit.create(EpisodesApi::class.java)
    }

    @Singleton
    @Provides
    fun providesEpisodesRepository(episodesApi: EpisodesApi): EpisodeRepository {
        return EpisodesRepositoryImpl(episodesApi)
    }

    @Singleton
    @Provides
    fun providesEpisodeDetailRepository(episodesApi: EpisodesApi): EpisodeDetailRepository {
        return EpisodesRepositoryImpl(episodesApi)
    }

}