package com.lagunadev.mycitiesweather.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.models.CityWeather

@Database(entities = [City::class, CityWeather::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CitiesWeatherRoomDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
    abstract fun citiesWeatherDao(): CitiesWeatherDao

    companion object {
        private var instance: CitiesWeatherRoomDatabase? = null

        fun getInstance(context: Context): CitiesWeatherRoomDatabase {
            if (instance == null) {

                synchronized(CitiesWeatherRoomDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            CitiesWeatherRoomDatabase::class.java,
                            "my_cities_weather_db"
                        )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}