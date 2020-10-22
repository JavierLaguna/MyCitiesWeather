package com.lagunadev.mycitiesweather.scenes.myCities

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.repository.db.CitiesWeatherRoomDatabase

class MyCitiesViewModel(private val context: Application, private val owner: LifecycleOwner) :
    ViewModel() {

    private val myCitiesRepository = CitiesWeatherRoomDatabase.getInstance(context).citiesDao()

    var delegate: MyCitiesViewModelDelegate? = null

    fun initialize() {
        getMyCities()
    }

    private fun getMyCities() {
        myCitiesRepository.getAllCities().observe(owner, Observer {
            delegate?.onUpdateCities(it)
        })
    }
}