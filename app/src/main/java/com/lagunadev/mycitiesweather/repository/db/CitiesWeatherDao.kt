package com.lagunadev.mycitiesweather.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lagunadev.mycitiesweather.models.CityWeather

@Dao
abstract class CitiesWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCityWeather(cityWeather: CityWeather)

    @Query("SELECT * FROM cities_weather_table WHERE cityId = :cityId")
    abstract fun getCityWeatherOf(cityId: Int): LiveData<CityWeather?>
}