package com.lagunadev.mycitiesweather.scenes.weatherList

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.WeatherItem
import com.lagunadev.mycitiesweather.utils.inflate


class WeatherListAdapter() : RecyclerView.Adapter<WeatherListAdapter.WeatherItemHolder>() {

    private val weatherList = mutableListOf<WeatherItem>()

    class WeatherItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weatherItem: WeatherItem? = null
            set(value) {
                field = value
                itemView.tag = field

            }
    }

    fun setWeather(weatherList: List<WeatherItem>) {
        this.weatherList.clear()
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemHolder {
        val view = parent.inflate(R.layout.item_weather)
        return WeatherItemHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherItemHolder, position: Int) {
        weatherList.get(position).let { weatherItem ->
            holder.weatherItem = weatherItem
        }
    }
}