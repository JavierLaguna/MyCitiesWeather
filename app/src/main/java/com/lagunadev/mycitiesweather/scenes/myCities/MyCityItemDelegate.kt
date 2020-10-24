package com.lagunadev.mycitiesweather.scenes.myCities

import com.lagunadev.mycitiesweather.models.City

interface MyCityItemDelegate {
    fun onSelectCity(city: City)
    fun onTapDeleteCity(city: City)
}