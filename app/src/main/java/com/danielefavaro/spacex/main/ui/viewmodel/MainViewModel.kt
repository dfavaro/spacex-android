package com.danielefavaro.spacex.main.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielefavaro.spacex.base.exception.NetworkUnavailableException
import com.danielefavaro.spacex.base.util.Result
import com.danielefavaro.spacex.main.domain.LaunchesRepository
import com.danielefavaro.spacex.main.ui.model.LaunchModelUI
import com.danielefavaro.spacex.main.ui.model.LaunchesFragmentEvent
import com.danielefavaro.spacex.main.ui.model.fromDomain
import kotlinx.coroutines.*
import javax.inject.Inject

// according to the service cache policy
const val POLL_PERIOD = 20000L

class MainViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository
) : ViewModel() {

    private val _event = MutableLiveData<LaunchesFragmentEvent>()
    val event: LiveData<LaunchesFragmentEvent> = _event

    private var job: Job? = null

    /**
     * This method will start the polling.
     * @param dispatcher: useful for testing
      */
    fun getPastLaunchesPoll(dispatcher: CoroutineDispatcher? = null) {
        viewModelScope.launch {
            job ?: run {
                // show loading only at first launch
                _event.value = LaunchesFragmentEvent.OnLoading(true)
            }
            job?.cancel()

            dispatcher?.let {
                // test environment
                withContext(it) {
                    job = getPastLaunches(this)
                }
            } ?: run {
                job = getPastLaunches(this)
            }
        }
    }

    private fun getPastLaunches(coroutineScope: CoroutineScope) = coroutineScope.launch {
        while (isActive) {
            when (val result = launchesRepository.getPastLaunches()) {
                is Result.Success -> {
                    _event.value = LaunchesFragmentEvent.OnLoading(false)
                    _event.value = LaunchesFragmentEvent.OnPastLaunches(result.data.map {
                        LaunchModelUI().fromDomain(it)
                    })
                    delay(POLL_PERIOD)
                }
                is Result.Error -> {
                    when (result.error) {
                        is NetworkUnavailableException -> {
                            onConnectionUnavailable()
                            delay(POLL_PERIOD)
                        }
                        else -> {
                            _event.value = LaunchesFragmentEvent.OnLoading(false)
                            _event.value = LaunchesFragmentEvent.OnGenericError
                        }
                    }
                }
            }
        }
    }

    fun onConnectionAvailable() {
        viewModelScope.launch {
            _event.value = LaunchesFragmentEvent.OnNetworkAvailable

            // resume poll only if not already started before
            job ?: getPastLaunchesPoll()
        }
    }

    fun onConnectionUnavailable() {
        viewModelScope.launch {
            _event.value = LaunchesFragmentEvent.OnNetworkUnavailable

            // stop poll
            job?.cancel()
            job = null
        }
    }
}