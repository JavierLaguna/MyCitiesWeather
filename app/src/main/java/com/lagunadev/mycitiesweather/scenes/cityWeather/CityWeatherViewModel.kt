package com.lagunadev.mycitiesweather.scenes.cityWeather

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.models.CityWeather
import com.lagunadev.mycitiesweather.models.CityWeatherResponse
import com.lagunadev.mycitiesweather.repository.GetWeatherRepository
import com.lagunadev.mycitiesweather.repository.db.CitiesWeatherRoomDatabase
import com.lagunadev.mycitiesweather.repository.services.GetWeatherServiceImpl
import com.lagunadev.mycitiesweather.repository.services.MetaweatherService
import com.lagunadev.mycitiesweather.scenes.weatherList.WeatherItemViewModel
import com.lagunadev.mycitiesweather.utils.ifLet
import retrofit2.Response

class CityWeatherViewModel(context: Application, private val owner: LifecycleOwner) :
    ViewModel() {

    private val weatherRepository: GetWeatherRepository = GetWeatherServiceImpl()
    private val myCitiesWeatherRepository =
        CitiesWeatherRoomDatabase.getInstance(context).citiesWeatherDao()

    private lateinit var city: City
    private var todayWeather: TodayWeatherViewModel? = null
        set(value) {
            field = value
            value?.let { delegate?.updateTodayWeather(it) }
        }
    private var nextDaysWeather: List<WeatherItemViewModel>? = null
        set(value) {
            field = value
            value?.let { delegate?.updateNextDaysWeather(it) }
        }
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value)
        }

    var delegate: CityWeatherViewModelDelegate? = null

    fun initialize(city: City) {
        this.city = city
        listenCityWeather()
    }

    private fun listenCityWeather() {
        getCityWeather(city, false)

        city.id?.let { cityId ->
            myCitiesWeatherRepository.getCityWeatherOf(cityId)
                .observe(owner, Observer { cityWeather ->
                    if (cityWeather != null) {
                        todayWeather = TodayWeatherViewModel(cityWeather.todayWeather)
                        nextDaysWeather =
                            cityWeather.nextDaysWeather.map { WeatherItemViewModel(it) }
                    } else {
                        isLoading = true
                    }
                })
        }
    }

    private fun saveCityWeather(cityWeatherResponse: CityWeatherResponse) {
        val cityId = city.id
        val todayWeather = cityWeatherResponse.consolidatedWeather?.first()
        val nextDaysWeather = cityWeatherResponse.consolidatedWeather
            ?.filterNotNull()
            ?.filter { it.id !== todayWeather?.id }


        ifLet(cityId, todayWeather, nextDaysWeather) {
            val cityWeather = CityWeather(cityId!!, todayWeather!!, nextDaysWeather!!)
            myCitiesWeatherRepository.insertCityWeather(cityWeather)
        }
    }

    fun getCityWeather(city: City, updateLoading: Boolean = true) {
        if (updateLoading) {
            isLoading = true
        }

        city.id?.let { cityId ->
            weatherRepository.getWeatherOf(
                cityId,
                object : MetaweatherService.CallbackResponse<CityWeatherResponse> {

                    override fun onResponse(response: CityWeatherResponse) {
                        saveCityWeather(response)
                        isLoading = false
                    }

                    override fun onFailure(t: Throwable, res: Response<*>?) {
                        delegate?.showError()
                        isLoading = false
                    }
                })
        }
    }
}