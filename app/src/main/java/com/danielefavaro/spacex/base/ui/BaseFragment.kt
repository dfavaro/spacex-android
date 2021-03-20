package com.danielefavaro.spacex.base.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.danielefavaro.spacex.base.util.ViewModelsFactory
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelsFactory: ViewModelsFactory

    abstract fun setupDI()

    override fun onAttach(context: Context) {
        setupDI()
        super.onAttach(context)
    }
}