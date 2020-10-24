package com.lagunadev.mycitiesweather.repository.services

import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.models.CityWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaweatherApi {

    @GET("api/location/search")
    @Headers("Content-Type: application/json")
    fun getCities(@Query("query") name: String): Call<List<City>>

    @GET("api/location/{cityId}")
    @Headers("Content-Type: application/json")
    fun getCityWeather(@Path("cityId") cityId: Int): Call<CityWeatherResponse>
}