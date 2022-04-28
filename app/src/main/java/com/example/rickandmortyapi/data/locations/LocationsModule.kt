package com.example.rickandmortyapi.data.locations

import com.example.rickandmortyapi.data.common.module.NetworkModule
import com.example.rickandmortyapi.data.locations.remote.LocationsApi
import com.example.rickandmortyapi.domain.locations.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class LocationsModule {

    @Singleton
    @Provides
    fun providesRetrofitClient(retrofit: Retrofit): LocationsApi {
        return retrofit.create(LocationsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesLocationsRepository(locationsApi: LocationsApi): LocationRepository {
        return LocationsRepositoryImpl(locationsApi)
    }

}