package com.danielefavaro.spacex.main.data

import com.danielefavaro.spacex.main.data.source.LaunchesRemoteDataSource
import com.danielefavaro.spacex.main.domain.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val remoteDataSource: LaunchesRemoteDataSource
) : LaunchesRepository {

    override suspend fun getPastLaunches() = remoteDataSource.getPastLaunches()

}