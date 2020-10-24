package com.lagunadev.mycitiesweather.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherItem(

    @field:SerializedName("visibility")
    val visibility: Double? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("applicable_date")
    val applicableDate: String? = null,

    @field:SerializedName("wind_direction")
    val windDirection: Double? = null,

    @field:SerializedName("predictability")
    val predictability: Int? = null,

    @field:SerializedName("wind_direction_compass")
    val windDirectionCompass: String? = null,

    @field:SerializedName("weather_state_name")
    val weatherStateName: String? = null,

    @field:SerializedName("min_temp")
    val minTemp: Double? = null,

    @field:SerializedName("weather_state_abbr")
    val weatherStateAbbr: String? = null,

    @field:SerializedName("the_temp")
    val theTemp: Double? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,

    @field:SerializedName("id")
    val id: Long? = null,

    @field:SerializedName("max_temp")
    val maxTemp: Double? = null,

    @field:SerializedName("air_pressure")
    val airPressure: Double? = null
) : Serializable