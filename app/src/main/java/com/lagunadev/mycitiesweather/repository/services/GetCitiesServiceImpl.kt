package com.lagunadev.mycitiesweather.repository.services

import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.repository.GetCitiesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCitiesServiceImpl : GetCitiesRepository {

    override fun getCities(name: String, cb: MetaweatherService.CallbackResponse<List<City>>) {

        MetaweatherService().metaweatherApi.getCities(name).enqueue(object : Callback<List<City>> {

            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                cb.onFailure(t)
            }

        })
    }
}