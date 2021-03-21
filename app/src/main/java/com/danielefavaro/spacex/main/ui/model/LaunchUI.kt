package com.danielefavaro.spacex.main.ui.model

import android.os.Parcelable
import com.danielefavaro.spacex.base.ktx.parseDate
import com.danielefavaro.spacex.data.entities.LaunchModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchModelUI(
    var success: Boolean = false,
    var name: String = "",
    var id: String = "",
    var date: String = "",
    var imageUrl: String = "",
    var details: String = ""
) : Parcelable

fun LaunchModelUI.fromDomain(launchModel: LaunchModel) = apply {
    success = launchModel.success
    name = launchModel.name
    id = launchModel.id
    imageUrl = launchModel.links.patch.large
    date = launchModel.dateUTC.parseDate()
    details = launchModel.details.orEmpty()
}