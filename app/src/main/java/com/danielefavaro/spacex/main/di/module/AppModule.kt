package com.danielefavaro.spacex.main.di.module

import com.danielefavaro.spacex.main.di.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class AppModule