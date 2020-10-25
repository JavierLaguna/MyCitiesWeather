package com.lagunadev.mycitiesweather.scenes.weatherList

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.WeatherItem
import com.lagunadev.mycitiesweather.utils.inflate
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat


class WeatherListAdapter() : RecyclerView.Adapter<WeatherListAdapter.WeatherItemHolder>() {

    private val weatherList = mutableListOf<WeatherItem>()

    class WeatherItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weatherItem: WeatherItem? = null
            set(value) {
                field = value
                itemView.tag = field

                val format = SimpleDateFormat("yyyy-MM-dd")
                val dateFormat = SimpleDateFormat("EEEE")
                val date = format.parse(value?.applicableDate)
                itemView.labelDay.text = dateFormat.format(date)

                itemView.labelTemp.text = "${"%.1f".format(value?.theTemp)}º"
                itemView.labelWind.text = "${"%.2f".format(value?.windSpeed)} mph"
                itemView.labelAirPreassure.text = "${value?.airPressure} mbar"
                itemView.labelHumidity.text = "${value?.humidity} %"
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