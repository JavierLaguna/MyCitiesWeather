package com.lagunadev.mycitiesweather.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lagunadev.mycitiesweather.scenes.addCities.AddCitiesViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(AddCitiesViewModel::class.java) -> AddCitiesViewModel()
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}