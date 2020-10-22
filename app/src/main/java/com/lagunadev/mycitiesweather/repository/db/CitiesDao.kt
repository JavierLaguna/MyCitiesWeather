package com.lagunadev.mycitiesweather.repository.db

import androidx.room.*
import com.lagunadev.mycitiesweather.models.City

@Dao
abstract class CitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCity(city: City)
}