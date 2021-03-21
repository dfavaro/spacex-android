package com.danielefavaro.spacex.main.data.source

import com.danielefavaro.spacex.base.util.Result
import com.danielefavaro.spacex.data.entities.LaunchModel

interface LaunchesDataSource {
    suspend fun getPastLaunches(): Result<List<LaunchModel>>
}