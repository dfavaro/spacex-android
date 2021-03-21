package com.danielefavaro.spacex.base.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danielefavaro.spacex.base.util.ViewModelsFactory
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    abstract fun setupDI()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDI()
        super.onCreate(savedInstanceState)
        registerForConnectionChanges()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterForConnectionChanges()
    }

    private var connectivityManager: ConnectivityManager? = null

    private val connectionCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            onConnectionAvailable()
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            onConnectionUnavailable()
        }
    }

    abstract fun onConnectionAvailable()
    abstract fun onConnectionUnavailable()

    private fun registerForConnectionChanges() {
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager?.registerDefaultNetworkCallback(connectionCallback)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager?.registerNetworkCallback(builder.build(), connectionCallback)
        }
    }

    private fun unregisterForConnectionChanges() {
        connectivityManager?.unregisterNetworkCallback(connectionCallback)
    }
}