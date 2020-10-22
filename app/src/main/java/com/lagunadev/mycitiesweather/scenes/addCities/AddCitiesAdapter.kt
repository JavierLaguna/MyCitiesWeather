package com.lagunadev.mycitiesweather.scenes.addCities

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.utils.inflate
import kotlinx.android.synthetic.main.item_add_city.view.*

class AddCitiesAdapter(private val addCityItemDelegate: AddCityItemDelegate? = null) :
    RecyclerView.Adapter<AddCitiesAdapter.AddCityHolder>() {

    private val cities = mutableListOf<City>()

    class AddCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCityHolder {
        val view = parent.inflate(R.layout.item_add_city)
        return AddCityHolder(view)
    }

    override fun onBindViewHolder(holder: AddCityHolder, position: Int) {
        cities.get(position).let { city ->
            holder.city = city

            holder.itemView.buttonAddCity.setOnClickListener {
                addCityItemDelegate?.onTapAddCity(city)
            }
        }
    }
}