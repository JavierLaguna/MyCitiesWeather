package com.lagunadev.mycitiesweather.repository

import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService

interface GetCitiesRepository {

    fun getCities(name: String, cb: MetaweatherService.CallbackResponse<List<City>>)
}