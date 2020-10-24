package com.lagunadev.mycitiesweather.scenes.cityWeather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.lagunadev.mycitiesweather.R

class CityWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setContentView(R.layout.activity_city_weather)

        setSupportActionBar(findViewById(R.id.toolbar))

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}