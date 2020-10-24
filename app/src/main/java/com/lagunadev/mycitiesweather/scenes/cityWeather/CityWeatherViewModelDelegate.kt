package com.lagunadev.mycitiesweather.scenes.cityWeather

import com.lagunadev.mycitiesweather.models.WeatherItem

interface CityWeatherViewModelDelegate {
    fun updateTodayWeather(weather: WeatherItem)
    fun updateNextDaysWeather(weathers: List<WeatherItem>)
}