package com.lagunadev.mycitiesweather.utils

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lagunadev.mycitiesweather.scenes.addCities.AddCitiesViewModel
import com.lagunadev.mycitiesweather.scenes.main.MainActivityViewModel
import com.lagunadev.mycitiesweather.scenes.myCities.MyCitiesViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(private val application: Application, private val owner: LifecycleOwner) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(application, owner)
                isAssignableFrom(AddCitiesViewModel::class.java) -> AddCitiesViewModel(application)
                isAssignableFrom(MyCitiesViewModel::class.java) -> MyCitiesViewModel(application, owner)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}