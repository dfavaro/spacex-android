package com.danielefavaro.spacex.main.di

import android.app.Application
import com.danielefavaro.spacex.main.di.module.AppModule
import com.danielefavaro.spacex.main.di.module.NetworkModule
import com.danielefavaro.spacex.main.di.module.SpaceXNetworkModule
import com.danielefavaro.spacex.main.di.module.ViewModelsFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        SpaceXNetworkModule::class,
        ViewModelsFactoryModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(application: Application)
    fun mainFactory(): MainComponent.Factory?
}