package com.lagunadev.mycitiesweather.scenes.addCities

import com.lagunadev.mycitiesweather.models.City

interface AddCityItemDelegate {
    fun onTapAddCity(city: City)
}