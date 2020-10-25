package com.lagunadev.mycitiesweather.scenes.weatherList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.utils.inflate
import kotlinx.android.synthetic.main.item_weather.view.*
import kotlinx.android.synthetic.main.item_weather.view.labelAirPreassure
import kotlinx.android.synthetic.main.item_weather.view.labelHumidity
import kotlinx.android.synthetic.main.item_weather.view.labelTemp
import kotlinx.android.synthetic.main.item_weather.view.labelWind


class WeatherListAdapter() : RecyclerView.Adapter<WeatherListAdapter.WeatherItemHolder>() {

    private val weatherList = mutableListOf<WeatherItemViewModel>()

    class WeatherItemHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        private val glide = Glide.with(context)
        var weatherItem: WeatherItemViewModel? = null
            set(value) {
                field = value
                itemView.tag = field

                value?.let {
                    glide.load(it.stateIconUrl)
                        .into(itemView.imgState)

                    itemView.labelDay.text = it.dateFormatted
                    itemView.labelTemp.text = it.tempFormatted
                    itemView.labelWind.text = it.windSpeedFormatted
                    itemView.labelAirPreassure.text = it.airPressureFormatted
                    itemView.labelHumidity.text = it.humidityFormatted
                }
            }
    }

    fun setWeather(weatherList: List<WeatherItemViewModel>) {
        this.weatherList.clear()
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemHolder {
        val view = parent.inflate(R.layout.item_weather)
        return WeatherItemHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: WeatherItemHolder, position: Int) {
        weatherList.get(position).let { weatherItem ->
            holder.weatherItem = weatherItem
        }
    }
}