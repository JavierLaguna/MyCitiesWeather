package com.lagunadev.mycitiesweather.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cities_weather_table")
data class CityWeather(

    @PrimaryKey
    val cityId: Int,

    val todayWeather: WeatherItem,

    val nextDaysWeather: List<WeatherItem>

) : Serializable