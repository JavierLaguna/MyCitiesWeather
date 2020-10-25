package com.lagunadev.mycitiesweather.scenes.cityWeather

import com.lagunadev.mycitiesweather.models.WeatherItem
import java.text.SimpleDateFormat
import java.util.*

class TodayWeatherViewModel(weatherItem: WeatherItem) {
    val temp: Double?
    val state: String?
    val stateAbbr: String?
    val date: Date?
    val windSpeed: Double?
    val airPressure: Double?
    val humidity: Int?
    val minTemp: Double?
    val maxTemp: Double?

    init {
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        this.temp = weatherItem.theTemp
        this.state = weatherItem.weatherStateName
        this.stateAbbr = weatherItem.weatherStateAbbr
        this.date = formatter.parse(weatherItem.applicableDate)
        this.windSpeed = weatherItem.windSpeed
        this.airPressure = weatherItem.airPressure
        this.humidity = weatherItem.humidity
        this.minTemp = weatherItem.minTemp
        this.maxTemp = weatherItem.maxTemp
    }

    // Computed properties
    val tempFormatted: String
        get() = "${"%.1f".format(temp)}º"

    val stateAbbrUrl: String
        get() = "background_${stateAbbr}"

    val dateFormatted: String
        get() {
            val dateFormat = SimpleDateFormat("EEEE, d MMM yyyy")
            return dateFormat.format(date).capitalize()
        }

    val windSpeedFormatted: String
        get() = "${"%.2f".format(windSpeed)} mph"

    val airPressureFormatted: String
        get() = "$airPressure mbar"

    val humidityFormatted: String
        get() = "$humidity %"

    val minTempFormatted: String
        get() = "${"%.1f".format(minTemp)}º"

    val maxTempFormatted: String
        get() = "${"%.1f".format(maxTemp)}º"
}
