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

    var delegate: AddCitiesViewModelDelegate? = null

    fun getCities(name: String) {
        citiesRepository.getCities(
            name,
            object : MetaweatherService.CallbackResponse<List<City>> {

                override fun onResponse(response: List<City>) {
                    cities = response
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun addCity(city: City) {
        myCitiesRepository.insertCity(city)
        delegate?.cityDidAdded()
    }
}