package com.lagunadev.mycitiesweather.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityWeatherResponse(

    @field:SerializedName("sun_set")
    val sunSet: String? = null,

    @field:SerializedName("parent")
    val parent: Parent? = null,

    @field:SerializedName("sources")
    val sources: List<SourcesItem?>? = null,

    @field:SerializedName("latt_long")
    val lattLong: String? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("timezone_name")
    val timezoneName: String? = null,

    @field:SerializedName("woeid")
    val woeid: Int? = null,

    @field:SerializedName("sun_rise")
    val sunRise: String? = null,

    @field:SerializedName("consolidated_weather")
    val consolidatedWeather: List<WeatherItem?>? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_type")
    val locationType: String? = null
) : Parcelable

@Parcelize
data class Parent(

    @field:SerializedName("latt_long")
    val lattLong: String? = null,

    @field:SerializedName("woeid")
    val woeid: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_type")
    val locationType: String? = null
) : Parcelable

@Parcelize
data class SourcesItem(

    @field:SerializedName("crawl_rate")
    val crawlRate: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) : Parcelable
