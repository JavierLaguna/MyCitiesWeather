package com.lagunadev.mycitiesweather.scenes.main

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.repository.db.CitiesWeatherRoomDatabase

class MainActivityViewModel(private val context: Application, private val owner: LifecycleOwner) :
    ViewModel() {

    private val myCitiesRepository = CitiesWeatherRoomDatabase.getInstance(context).citiesDao()

    var delegate: MainActivityViewModelDelegate? = null

    fun initialize() {
        myCitiesRepository.getAllCities().observe(owner, Observer {
            if (it.isNotEmpty()) {
                delegate?.citiesFound()
            } else {
                delegate?.noCitiesFound()
            }
        })
    }
}