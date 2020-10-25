package com.lagunadev.mycitiesweather.scenes.addCities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.scenes.main.MainActivity
import com.lagunadev.mycitiesweather.scenes.noCities.NoCitiesFragment
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_add_cities.*

class AddCitiesActivity : AppCompatActivity(), AddCitiesViewModelDelegate, AddCityItemDelegate {

    private val viewModel: AddCitiesViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(AddCitiesViewModel::class.java)
    }
    private val citiesAdapter: AddCitiesAdapter by lazy {
        val adapter = AddCitiesAdapter(this)
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cities)

        initialize()
        setListeners()
    }

    private fun initialize() {
        viewModel.delegate = this

        searchCity.setIconifiedByDefault(false)

        listCities.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCities.adapter = citiesAdapter
    }

    private fun setListeners() {
        searchCity.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.getCities(newText ?: "")
                return true
            }
        })
    }

    // AddCitiesViewModelDelegate
    override fun onUpdateCities(cities: List<City>) {
        citiesAdapter.setCities(cities)
    }

    override fun cityDidAdded() {
        finish()
    }

    override fun showError() {
        Snackbar.make(container, R.string.error_default, Snackbar.LENGTH_SHORT).show()
    }

    // AddCityItemDelegate
    override fun onTapAddCity(city: City) {
        viewModel.addCity(city)
    }
}