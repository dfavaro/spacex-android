package com.danielefavaro.spacex.main.ui.viewmodel

import com.danielefavaro.spacex.base.BaseUnitTest
import com.danielefavaro.spacex.base.ktx.assertValues
import com.danielefavaro.spacex.base.util.Result
import com.danielefavaro.spacex.main.ui.model.LaunchModelUI
import com.danielefavaro.spacex.main.ui.model.LaunchesFragmentEvent
import com.danielefavaro.spacex.main.ui.model.fromDomain
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : BaseUnitTest() {

    @Test
    fun `check getPastLaunches response`() = runBlocking {
        when (val result = launchesRepository.getPastLaunches()) {
            is Result.Success -> {
                assert(result.data.isNotEmpty())
            }
        }
    }

    @Test
    fun `check getPastLaunches fromDomain`() = runBlocking {
        when (val result = launchesRepository.getPastLaunches()) {
            is Result.Success -> {
                assert(result.data.isNotEmpty())

                val uiModel = result.data.map {
                    LaunchModelUI().fromDomain(it)
                }
                assert(uiModel.isNotEmpty())
                assert(uiModel.size == result.data.size)
            }
        }
    }

    @Test
    fun `check getPastLaunches work-flow`() = runBlocking {
        val result = launchesRepository.getPastLaunches() as Result.Success
        val uiModel = result.data.map {
            LaunchModelUI().fromDomain(it)
        }

        mainViewModel.event.assertValues(
            listOf(
                LaunchesFragmentEvent.OnLoading(true),
                LaunchesFragmentEvent.OnLoading(false),
                LaunchesFragmentEvent.OnPastLaunches(uiModel),
            )
        ) {
            mainViewModel.getPastLaunchesPoll(coroutineRule.testDispatcher)
            coroutineRule.testDispatcher.advanceTimeBy(POLL_PERIOD + 1000)
            coroutineRule.testDispatcher.cancel()
        }
    }

    @Test
    fun `check onConnectionAvailable`() = runBlocking {
        mainViewModel.event.assertValues(
            listOf(
                LaunchesFragmentEvent.OnNetworkAvailable,
                LaunchesFragmentEvent.OnLoading(true),
            )
        ) {
            mainViewModel.onConnectionAvailable()
        }
    }

    @Test
    fun `check onConnectionUnavailable`() = runBlocking {
        mainViewModel.event.assertValues(
            listOf(
                LaunchesFragmentEvent.OnNetworkUnavailable,
            )
        ) {
            mainViewModel.onConnectionUnavailable()
        }
    }
}