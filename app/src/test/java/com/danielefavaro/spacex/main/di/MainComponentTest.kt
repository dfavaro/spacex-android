package com.danielefavaro.spacex.main.di

import com.danielefavaro.spacex.base.BaseUnitTest
import com.danielefavaro.spacex.main.di.module.MainBindingModuleTest
import com.danielefavaro.spacex.main.di.module.NetworkModuleTest
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(
    modules = [
        NetworkModuleTest::class,
        MainBindingModuleTest::class,
    ]
)
interface MainComponentTest {
    @Component.Factory
    interface Factory {
        fun create(): MainComponentTest
    }

    fun inject(base: BaseUnitTest)
}