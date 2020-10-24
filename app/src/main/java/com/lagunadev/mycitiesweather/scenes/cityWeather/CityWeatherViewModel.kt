package com.lagunadev.mycitiesweather.scenes.cityWeather

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.models.CityWeather
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
    private val myCitiesWeatherRepository =
        CitiesWeatherRoomDatabase.getInstance(context).citiesWeatherDao()

    private lateinit var city: City
    private var todayWeather: WeatherItem? = null
        set(value) {
            field = value
            value?.let { delegate?.updateTodayWeather(it) }
        }

    var delegate: CityWeatherViewModelDelegate? = null

    fun initialize(city: City) {
        this.city = city
        listenCityWeather()
    }

    private fun listenCityWeather() {
        getCityWeather(city)

        city.id?.let { cityId ->
            myCitiesWeatherRepository.getCityWeatherOf(cityId)
                .observe(owner, Observer { cityWeather ->
                    if (cityWeather != null) {
                        todayWeather = cityWeather.todayWeather
                    }
                })
        }
    }

    private fun getCityWeather(city: City) {

        city.id?.let { cityId ->
            weatherRepository.getWeatherOf(
                cityId,
                object : MetaweatherService.CallbackResponse<CityWeatherResponse> {

                    override fun onResponse(response: CityWeatherResponse) {
                        saveCityWeather(response)
                    }

                    override fun onFailure(t: Throwable, res: Response<*>?) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }

    private fun saveCityWeather(cityWeatherResponse: CityWeatherResponse) {
        cityWeatherResponse.consolidatedWeather?.first()?.let { todayWeather ->
            val nextDaysWeather =
                cityWeatherResponse.consolidatedWeather.filter { it?.id !== todayWeather.id }
                    .filterNotNull()

            city.id?.let { cityId ->
                nextDaysWeather.let { nextDaysWeather ->
                    val cityWeather = CityWeather(cityId, todayWeather, nextDaysWeather)
                    myCitiesWeatherRepository.insertCityWeather(cityWeather)
                }
            }
        }

    }
}