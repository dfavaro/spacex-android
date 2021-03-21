package com.danielefavaro.spacex.data.entities

import com.google.gson.annotations.SerializedName

data class LaunchModel(
    val fairings: Any? = null,
    val links: Links,
    @SerializedName("static_fire_date_utc")
    val staticFireDateUTC: String,
    @SerializedName("static_fire_date_unix")
    val staticFireDateUnix: Long,
    val tdb: Boolean,
    val net: Boolean,
    val window: Long,
    val rocket: String,
    val success: Boolean,
    val failures: List<Any?>,
    val details: String?,
    val crew: List<Any?>,
    val ships: List<Any?>,
    val capsules: List<String>,
    val payloads: List<String>,
    val launchpad: String,
    @SerializedName("auto_update")
    val autoUpdate: Boolean,
    @SerializedName("flight_number")
    val flightNumber: Long,
    val name: String,
    @SerializedName("date_utc")
    val dateUTC: String,
    @SerializedName("date_unix")
    val dateUnix: Long,
    @SerializedName("date_local")
    val dateLocal: String,
    @SerializedName("date_precision")
    val datePrecision: String,
    val upcoming: Boolean,
    val cores: List<Core>,
    val id: String
)

data class Core(
    val core: String,
    val flight: Long,
    val gridfins: Boolean,
    val legs: Boolean,
    val reused: Boolean,
    @SerializedName("landing_attempt")
    val landingAttempt: Boolean,
    @SerializedName("landing_success")
    val landingSuccess: Boolean,
    @SerializedName("landing_type")
    val landingType: String,
    val landpad: String
)

data class Links(
    val patch: Patch,
    val reddit: Reddit,
    val flickr: Flickr,
    val presskit: String,
    val webcast: String,
    @SerializedName("youtube_id")
    val youtubeID: String,
    val article: String,
    val wikipedia: String
)

data class Flickr(
    val small: List<Any?>,
    val original: List<String>
)

data class Patch(
    val small: String,
    val large: String
)

data class Reddit(
    val campaign: String,
    val launch: String,
    val media: String,
    val recovery: Any? = null
)
