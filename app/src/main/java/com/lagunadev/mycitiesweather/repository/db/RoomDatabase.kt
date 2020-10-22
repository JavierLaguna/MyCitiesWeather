package com.lagunadev.mycitiesweather.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lagunadev.mycitiesweather.models.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CitiesWeatherRoomDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao

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