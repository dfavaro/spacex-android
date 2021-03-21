package com.danielefavaro.spacex.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.danielefavaro.spacex.SpaceXApp
import com.danielefavaro.spacex.base.ui.BaseActivity
import com.danielefavaro.spacex.databinding.MainActivityBinding
import com.danielefavaro.spacex.main.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewModel: MainViewModel by viewModels { viewModelsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(root)
        }
    }

    override fun setupDI() {
        (application as SpaceXApp).appComponent.mainFactory()?.create()?.inject(this)
    }

    override fun onConnectionAvailable() {
        viewModel.onConnectionAvailable()
    }

    override fun onConnectionUnavailable() {
        viewModel.onConnectionUnavailable()
    }
}