package com.danielefavaro.spacex

import android.app.Application
import com.danielefavaro.spacex.main.di.AppComponent
import com.danielefavaro.spacex.main.di.DaggerAppComponent

class SpaceXApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}