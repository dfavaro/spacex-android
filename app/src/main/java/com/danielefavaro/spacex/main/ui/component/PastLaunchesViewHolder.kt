package com.danielefavaro.spacex.main.ui.component

import androidx.recyclerview.widget.RecyclerView
import com.danielefavaro.spacex.base.ktx.load
import com.danielefavaro.spacex.databinding.PastLaunchesItemLayoutBinding
import com.danielefavaro.spacex.main.ui.model.LaunchModelUI

class PastLaunchesViewHolder(
    private val binding: PastLaunchesItemLayoutBinding,
    private val onClick: (launch: LaunchModelUI) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchModelUI) {
        binding.rocketName.text = item.name
        binding.date.text = item.date
        binding.launchImage.load(item.imageUrl)

        binding.root.setOnClickListener {
            onClick.invoke(item)
        }
    }

}