package com.danielefavaro.spacex.main.domain

import com.danielefavaro.spacex.base.util.Result
import com.danielefavaro.spacex.data.entities.LaunchModel

interface LaunchesRepository {
    suspend fun getPastLaunches(): Result<List<LaunchModel>>
}