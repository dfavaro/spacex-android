package com.danielefavaro.spacex.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danielefavaro.spacex.SpaceXApp
import com.danielefavaro.spacex.base.ktx.load
import com.danielefavaro.spacex.base.ui.BaseBottomSheetDialogFragment
import com.danielefavaro.spacex.base.util.Constants
import com.danielefavaro.spacex.databinding.LaunchDetailFragmentBinding
import com.danielefavaro.spacex.main.ui.model.LaunchModelUI

class LaunchDetailFragment : BaseBottomSheetDialogFragment() {

    private var _binding: LaunchDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = LaunchDetailFragmentBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun setupDI() {
        (activity?.application as? SpaceXApp)?.appComponent?.mainFactory()?.create()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<LaunchModelUI>(Constants.SHARED_LAUNCH)?.let { launchModelUI ->
            binding.rocketName.text = launchModelUI.name
            binding.date.text = launchModelUI.date
            binding.launchImage.load(launchModelUI.imageUrl)
            binding.details.text = launchModelUI.details
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}