package com.lagunadev.mycitiesweather.scenes.myCities

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.utils.inflate
import kotlinx.android.synthetic.main.item_my_city.view.*


class MyCitiesAdapter() : RecyclerView.Adapter<MyCitiesAdapter.MyCityHolder>() {

    private val cities = mutableListOf<City>()

    class MyCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var city: City? = null
            set(value) {
                field = value
                itemView.tag = field

                field?.let {
                    itemView.labelCity.text = it.title
                }
            }
    }

    fun setCities(cities: List<City>) {
        this.cities.clear()
        this.cities.addAll(cities)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCityHolder {
        val view = parent.inflate(R.layout.item_my_city)
        return MyCityHolder(view)
    }

    override fun onBindViewHolder(holder: MyCityHolder, position: Int) {
        cities.get(position).let { city ->
            holder.city = city
        }
    }
}