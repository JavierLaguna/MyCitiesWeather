package com.lagunadev.mycitiesweather.repository.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lagunadev.mycitiesweather.models.City

@Dao
abstract class CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCity(city: City)

    @Query("SELECT * FROM cities_table")
    abstract fun getAllCities(): LiveData<List<City>>
}