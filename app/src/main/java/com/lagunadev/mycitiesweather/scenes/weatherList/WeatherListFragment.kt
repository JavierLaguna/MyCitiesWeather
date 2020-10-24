package com.lagunadev.mycitiesweather.scenes.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.WeatherItem
import kotlinx.android.synthetic.main.fragment_weather_list.*


class WeatherListFragment : Fragment() {

    private val weatherAdapter: WeatherListAdapter by lazy {
        val adapter = WeatherListAdapter()
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        weatherList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        weatherList.adapter = weatherAdapter
    }

    fun setWeather(weatherList: List<WeatherItem>) {
        weatherAdapter.setWeather(weatherList)
    }
}