package com.danielefavaro.spacex.main.di.module

import com.danielefavaro.spacex.main.data.LaunchesRepositoryImpl
import com.danielefavaro.spacex.main.data.source.LaunchesDataSource
import com.danielefavaro.spacex.main.data.source.LaunchesRemoteDataSource
import com.danielefavaro.spacex.main.domain.LaunchesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MainBindingModuleTest {

    @Binds
    abstract fun bindsMainRepository(repository: LaunchesRepositoryImpl): LaunchesRepository

    @Binds
    abstract fun bindsMainDataSource(source: LaunchesRemoteDataSource): LaunchesDataSource
}