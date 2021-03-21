package com.danielefavaro.spacex.data.service

import com.danielefavaro.spacex.data.entities.LaunchModel
import retrofit2.http.GET

interface SpaceXService {

    @GET("launches/past")
    suspend fun getPastLaunches(): List<LaunchModel>
}