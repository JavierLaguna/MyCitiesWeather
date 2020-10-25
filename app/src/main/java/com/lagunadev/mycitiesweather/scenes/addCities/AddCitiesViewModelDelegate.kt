package com.lagunadev.mycitiesweather.scenes.addCities

import com.lagunadev.mycitiesweather.models.City

interface AddCitiesViewModelDelegate {
    fun onUpdateCities(cities: List<City>)
    fun cityDidAdded()
    fun showError()
}