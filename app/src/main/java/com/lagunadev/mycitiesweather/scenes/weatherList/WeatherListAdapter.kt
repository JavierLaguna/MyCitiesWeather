package com.lagunadev.mycitiesweather.scenes.weatherList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lagunadev.mycitiesweather.BuildConfig
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.WeatherItem
import com.lagunadev.mycitiesweather.utils.inflate
import kotlinx.android.synthetic.main.item_weather.view.*
import kotlinx.android.synthetic.main.item_weather.view.labelAirPreassure
import kotlinx.android.synthetic.main.item_weather.view.labelHumidity
import kotlinx.android.synthetic.main.item_weather.view.labelTemp
import kotlinx.android.synthetic.main.item_weather.view.labelWind
import java.text.SimpleDateFormat


class WeatherListAdapter() : RecyclerView.Adapter<WeatherListAdapter.WeatherItemHolder>() {

    private val weatherList = mutableListOf<WeatherItem>()

    class WeatherItemHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        private val glide = Glide.with(context)
        var weatherItem: WeatherItem? = null
            set(value) {
                field = value
                itemView.tag = field

                value?.let {
                    glide.load("${BuildConfig.WeatherApiDomain}static/img/weather/ico/${value.weatherStateAbbr}.ico")
                        .into(itemView.imgState)

                    val format = SimpleDateFormat("yyyy-MM-dd")
                    val dateFormat = SimpleDateFormat("EEEE")
                    val date = format.parse(value.applicableDate)
                    itemView.labelDay.text = dateFormat.format(date)

                    itemView.labelTemp.text = "${"%.1f".format(value.theTemp)}º"
                    itemView.labelWind.text = "${"%.2f".format(value.windSpeed)} mph"
                    itemView.labelAirPreassure.text = "${value.airPressure} mbar"
                    itemView.labelHumidity.text = "${value.humidity} %"
                }
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
        return WeatherItemHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: WeatherItemHolder, position: Int) {
        weatherList.get(position).let { weatherItem ->
            holder.weatherItem = weatherItem
        }
    }
}