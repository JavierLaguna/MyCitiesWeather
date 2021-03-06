package com.lagunadev.mycitiesweather.scenes.addCities

import android.app.Application
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.repository.GetCitiesRepository
import com.lagunadev.mycitiesweather.repository.db.CitiesWeatherRoomDatabase
import com.lagunadev.mycitiesweather.repository.services.GetCitiesServiceImpl
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService
import retrofit2.Response

class AddCitiesViewModel(private val context: Application) : ViewModel() {

    private val citiesRepository: GetCitiesRepository = GetCitiesServiceImpl()
    private val myCitiesRepository = CitiesWeatherRoomDatabase.getInstance(context).citiesDao()
    private var cities = listOf<City>()
        set(value) {
            field = value
            delegate?.onUpdateCities(cities)
        }
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value)
        }

    var delegate: AddCitiesViewModelDelegate? = null

    fun getCities(name: String) {
        isLoading = true

        citiesRepository.getCities(
            name,
            object : MetaweatherService.CallbackResponse<List<City>> {

                override fun onResponse(response: List<City>) {
                    cities = response
                    isLoading = false
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    delegate?.showError()
                    isLoading = false
                }
            })
    }

    fun addCity(city: City) {
        myCitiesRepository.insertCity(city)
        delegate?.cityDidAdded()
    }
}