package com.danielefavaro.spacex.main.di.module

import androidx.lifecycle.ViewModel
import com.danielefavaro.spacex.main.di.scope.ViewModelKey
import com.danielefavaro.spacex.main.data.LaunchesRepositoryImpl
import com.danielefavaro.spacex.main.data.source.LaunchesDataSource
import com.danielefavaro.spacex.main.data.source.LaunchesRemoteDataSource
import com.danielefavaro.spacex.main.domain.LaunchesRepository
import com.danielefavaro.spacex.main.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsLaunchesRepository(repository: LaunchesRepositoryImpl): LaunchesRepository

    @Binds
    abstract fun bindsLaunchesRemoteDataSource(source: LaunchesRemoteDataSource): LaunchesDataSource
}