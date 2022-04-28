package com.example.rickandmortyapi.data.characters

import com.example.rickandmortyapi.data.characters.remote.CharactersApi
import com.example.rickandmortyapi.data.common.module.NetworkModule
import com.example.rickandmortyapi.domain.characters.repository.CharacterDetailRepository
import com.example.rickandmortyapi.domain.characters.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class CharactersModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCharactersRepository(charactersApi: CharactersApi): CharactersRepository {
        return CharactersRepositoryImpl(charactersApi)
    }

    @Singleton
    @Provides
    fun providesCharactersDetailRepository(charactersApi: CharactersApi): CharacterDetailRepository {
        return CharactersRepositoryImpl(charactersApi)
    }
}