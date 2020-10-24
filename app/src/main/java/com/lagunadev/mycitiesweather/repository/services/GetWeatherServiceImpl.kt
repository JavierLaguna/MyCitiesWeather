package com.lagunadev.mycitiesweather.repository.services

import com.lagunadev.mycitiesweather.models.CityWeatherResponse
import com.lagunadev.mycitiesweather.repository.GetWeatherRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetWeatherServiceImpl : GetWeatherRepository {

    override fun getWeatherOf(
        cityId: Int,
        cb: MetaweatherService.CallbackResponse<CityWeatherResponse>
    ) {

        MetaweatherService().metaweatherApi.getCityWeather(cityId)
            .enqueue(object : Callback<CityWeatherResponse> {

                override fun onResponse(
                    call: Call<CityWeatherResponse>,
                    response: Response<CityWeatherResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

                override fun onFailure(call: Call<CityWeatherResponse>, t: Throwable) {
                    cb.onFailure(t)
                }

            })
    }
}