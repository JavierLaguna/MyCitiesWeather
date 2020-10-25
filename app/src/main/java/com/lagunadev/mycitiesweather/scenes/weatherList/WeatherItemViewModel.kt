package com.lagunadev.mycitiesweather.scenes.weatherList

import com.lagunadev.mycitiesweather.BuildConfig
import com.lagunadev.mycitiesweather.models.WeatherItem
import java.text.SimpleDateFormat
import java.util.*

class WeatherItemViewModel(weatherItem: WeatherItem) {
    val temp: Double?
    val stateAbbr: String?
    val date: Date?
    val windSpeed: Double?
    val airPressure: Double?
    val humidity: Int?

    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        this.temp = weatherItem.theTemp
        this.stateAbbr = weatherItem.weatherStateAbbr
        this.date = formatter.parse(weatherItem.applicableDate)
        this.windSpeed = weatherItem.windSpeed
        this.airPressure = weatherItem.airPressure
        this.humidity = weatherItem.humidity
    }

    // Computed properties
    val tempFormatted: String
        get() = "${"%.1f".format(temp)}º"

    val stateIconUrl: String
        get() = "${BuildConfig.WeatherApiDomain}static/img/weather/ico/${stateAbbr}.ico"

    val dateFormatted: String
        get() {
            val dateFormat = SimpleDateFormat("EEEE")
            return dateFormat.format(date).capitalize()
        }

    val windSpeedFormatted: String
        get() = "${"%.2f".format(windSpeed)} mph"

    val airPressureFormatted: String
        get() = "$airPressure mbar"

    val humidityFormatted: String
        get() = "$humidity %"
}
