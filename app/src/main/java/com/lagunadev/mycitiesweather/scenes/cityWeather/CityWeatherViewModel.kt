package com.lagunadev.mycitiesweather.scenes.cityWeather

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.models.CityWeatherResponse
import com.lagunadev.mycitiesweather.models.WeatherItem
import com.lagunadev.mycitiesweather.repository.GetWeatherRepository
import com.lagunadev.mycitiesweather.repository.db.CitiesWeatherRoomDatabase
import com.lagunadev.mycitiesweather.repository.services.GetWeatherServiceImpl
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService
import retrofit2.Response

class CityWeatherViewModel(private val context: Application, private val owner: LifecycleOwner) :
    ViewModel() {

    private val weatherRepository: GetWeatherRepository = GetWeatherServiceImpl()
    private val myCitiesRepository = CitiesWeatherRoomDatabase.getInstance(context).citiesDao()

    private lateinit var city: City
    private var todayWeather: WeatherItem? = null
        set(value) {
            field = value
            value?.let { delegate?.updateTodayWeather(it) }
        }

    var delegate: CityWeatherViewModelDelegate? = null

    fun initialize(city: City) {
        this.city = city
        getCityWeather(city)
    }

    private fun getCityWeather(city: City) {

        city.id?.let { cityId ->
            weatherRepository.getWeatherOf(
                cityId,
                object : MetaweatherService.CallbackResponse<CityWeatherResponse> {

                    override fun onResponse(response: CityWeatherResponse) {
                        todayWeather = response.consolidatedWeather?.first()
                    }

                    override fun onFailure(t: Throwable, res: Response<*>?) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}