package com.danielefavaro.spacex.main.di

import com.danielefavaro.spacex.main.di.module.MainBindingModule
import com.danielefavaro.spacex.main.di.scope.MainScope
import com.danielefavaro.spacex.main.ui.LaunchDetailFragment
import com.danielefavaro.spacex.main.ui.LaunchesFragment
import com.danielefavaro.spacex.main.ui.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        MainBindingModule::class
    ]
)
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(launchesFragment: LaunchesFragment)
    fun inject(launchDetailFragment: LaunchDetailFragment)
}