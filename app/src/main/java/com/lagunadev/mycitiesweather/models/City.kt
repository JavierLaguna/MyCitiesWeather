package com.lagunadev.mycitiesweather.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(

    @field:SerializedName("woeid")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_type")
    val locationType: String? = null,

    @field:SerializedName("latt_long")
    val lattLong: String? = null

) : Serializable
