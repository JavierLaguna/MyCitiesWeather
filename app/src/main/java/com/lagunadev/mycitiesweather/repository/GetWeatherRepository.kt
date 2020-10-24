package com.lagunadev.mycitiesweather.repository

import com.lagunadev.mycitiesweather.models.CityWeatherResponse
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService

interface GetWeatherRepository {

    fun getWeatherOf(cityId: Int, cb: MetaweatherService.CallbackResponse<CityWeatherResponse>)
}