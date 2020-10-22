package com.lagunadev.mycitiesweather.scenes.myCities

import com.lagunadev.mycitiesweather.models.City

interface MyCitiesViewModelDelegate {
    fun onUpdateCities(cities: List<City>)
}