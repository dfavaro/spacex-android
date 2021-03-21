package com.danielefavaro.spacex.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.danielefavaro.spacex.databinding.PastLaunchesItemLayoutBinding
import com.danielefavaro.spacex.main.ui.component.PastLaunchesViewHolder
import com.danielefavaro.spacex.main.ui.model.LaunchModelUI

class PastLaunchesAdapter(
    private var onClick: (launch: LaunchModelUI) -> Unit
) : ListAdapter<LaunchModelUI, PastLaunchesViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PastLaunchesViewHolder(
        PastLaunchesItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onClick
    )

    override fun onBindViewHolder(holder: PastLaunchesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<LaunchModelUI>() {
            override fun areContentsTheSame(oldItem: LaunchModelUI, newItem: LaunchModelUI) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: LaunchModelUI, newItem: LaunchModelUI) =
                oldItem.id == newItem.id
        }
    }
}