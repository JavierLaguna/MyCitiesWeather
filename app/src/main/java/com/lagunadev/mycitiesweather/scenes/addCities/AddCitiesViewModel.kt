package com.lagunadev.mycitiesweather.scenes.addCities

import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.repository.GetCitiesRepository
import com.lagunadev.mycitiesweather.repository.services.GetCitiesServiceImpl
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService
import retrofit2.Response

interface AddCitiesViewModelDelegate {
    fun onUpdateCities(cities: List<City>)
}

class AddCitiesViewModel : ViewModel() {

    private val citiesRepositoryRepository: GetCitiesRepository = GetCitiesServiceImpl()
    private var cities = listOf<City>()
        set(value) {
            field = value
            delegate?.onUpdateCities(cities)
        }

    var delegate: AddCitiesViewModelDelegate? = null

    fun getCities(name: String) {
        citiesRepositoryRepository.getCities(
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
}