package com.danielefavaro.spacex.main.di.module

import com.danielefavaro.spacex.data.service.SpaceXService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SpaceXNetworkModule {

    @Singleton
    @Provides
    fun providesSpaceXService(retrofit: Retrofit): SpaceXService =
        retrofit.create(SpaceXService::class.java)
}