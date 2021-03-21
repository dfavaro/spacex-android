package com.danielefavaro.spacex.main.ui.model

sealed class LaunchesFragmentEvent {
    data class OnLoading(val loading: Boolean) : LaunchesFragmentEvent()
    object OnGenericError : LaunchesFragmentEvent()
    object OnNetworkUnavailable : LaunchesFragmentEvent()
    object OnNetworkAvailable : LaunchesFragmentEvent()
    data class OnPastLaunches(val launches: List<LaunchModelUI>) : LaunchesFragmentEvent()
}