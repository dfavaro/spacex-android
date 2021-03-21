package com.danielefavaro.spacex.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danielefavaro.spacex.main.di.DaggerMainComponentTest
import com.danielefavaro.spacex.main.di.MainComponentTest
import com.danielefavaro.spacex.main.domain.LaunchesRepository
import com.danielefavaro.spacex.main.ui.viewmodel.MainViewModel
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
open class BaseUnitTest {

    val component: MainComponentTest = DaggerMainComponentTest.create()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = TestCoroutineRule()

    @Inject
    lateinit var launchesRepository: LaunchesRepository

    @Inject
    lateinit var mockWebServer: MockWebServer

    lateinit var mainViewModel: MainViewModel

    @Before
    open fun setUp() {
        component.inject(this)

        mockStuff()

        mainViewModel = MainViewModel(launchesRepository)
        mainViewModel = spyk(mainViewModel)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun mockStuff() {
        // TODO
    }
}