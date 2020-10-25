package com.lagunadev.mycitiesweather.scenes.cityWeather

import com.lagunadev.mycitiesweather.scenes.weatherList.WeatherItemViewModel

interface CityWeatherViewModelDelegate {
    fun updateTodayWeather(todayWeather: TodayWeatherViewModel)
    fun updateNextDaysWeather(nextDaysWeather: List<WeatherItemViewModel>)
    fun showError()
    fun updateLoadingState(isLoading: Boolean)
}