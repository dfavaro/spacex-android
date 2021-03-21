package com.danielefavaro.spacex.main.data.source

import com.danielefavaro.spacex.base.exception.NetworkGenericException
import com.danielefavaro.spacex.base.exception.NetworkUnavailableException
import com.danielefavaro.spacex.base.util.Result
import com.danielefavaro.spacex.data.service.SpaceXService
import java.net.UnknownHostException
import javax.inject.Inject

class LaunchesRemoteDataSource @Inject constructor(
    private val spaceXService: SpaceXService
) : LaunchesDataSource {

    override suspend fun getPastLaunches() = try {
        val result = spaceXService.getPastLaunches()
        Result.Success(result)
    } catch (e: UnknownHostException) {
        Result.Error(NetworkUnavailableException())
    } catch (e: Exception) {
        Result.Error(NetworkGenericException())
    }
}