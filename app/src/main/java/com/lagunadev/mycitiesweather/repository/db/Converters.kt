package com.lagunadev.mycitiesweather.repository.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.lagunadev.mycitiesweather.models.WeatherItem

class Converters {

    @TypeConverter
    fun weatherItemToJson(value: WeatherItem) = Gson().toJson(value)

    @TypeConverter
    fun weatherItemListToJson(value: List<WeatherItem>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToWeatherItem(value: String) = Gson().fromJson(value, WeatherItem::class.java)

    @TypeConverter
    fun jsonToWeatherItemList(value: String) = Gson().fromJson(value, Array<WeatherItem>::class.java).toList()
}