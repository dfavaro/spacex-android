package com.danielefavaro.spacex.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielefavaro.spacex.R
import com.danielefavaro.spacex.SpaceXApp
import com.danielefavaro.spacex.base.ui.BaseFragment
import com.danielefavaro.spacex.base.util.Constants
import com.danielefavaro.spacex.databinding.LaunchesFragmentBinding
import com.danielefavaro.spacex.main.ui.adapter.PastLaunchesAdapter
import com.danielefavaro.spacex.main.ui.model.LaunchesFragmentEvent
import com.danielefavaro.spacex.main.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class LaunchesFragment : BaseFragment() {

    private var _binding: LaunchesFragmentBinding? = null
    private val binding get() = _binding!!

    private val pastLaunchesAdapter = PastLaunchesAdapter(onClick = { launch ->
        findNavController().navigate(
            R.id.action_launchesFragment_to_launchDetailFragment, bundleOf(
                Constants.SHARED_LAUNCH to launch
            )
        )
    })

    private var snackbarIndefinite: Snackbar? = null

    private val viewModel: MainViewModel by activityViewModels { viewModelsFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = LaunchesFragmentBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun setupDI() {
        (activity?.application as? SpaceXApp)?.appComponent?.mainFactory()?.create()?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeEvents()
        viewModel.getPastLaunchesPoll()
    }

    private fun initViews() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pastLaunchesAdapter
        }
    }

    private fun observeEvents() {
        viewModel.event.observe(viewLifecycleOwner, { event ->
            when (event) {
                is LaunchesFragmentEvent.OnLoading -> binding.loadingLayout.root.visibility =
                    if (event.loading) View.VISIBLE else View.GONE
                is LaunchesFragmentEvent.OnGenericError -> {
                    // TODO Custom error view
                    Toast.makeText(context, R.string.generic_error, Toast.LENGTH_LONG).show()
                }
                is LaunchesFragmentEvent.OnPastLaunches -> {
                    pastLaunchesAdapter.submitList(event.launches)
                }
                is LaunchesFragmentEvent.OnNetworkAvailable -> {
                    snackbarIndefinite?.dismiss()
                    snackbarIndefinite = null
                }
                is LaunchesFragmentEvent.OnNetworkUnavailable -> {
                    // TODO Custom error view
                    snackbarIndefinite = Snackbar.make(
                        binding.root,
                        R.string.network_unavailable,
                        Snackbar.LENGTH_INDEFINITE
                    )
                    snackbarIndefinite?.show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}