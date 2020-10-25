package com.lagunadev.mycitiesweather.scenes.addCities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_add_cities.*
import kotlinx.android.synthetic.main.activity_add_cities.container
import kotlinx.android.synthetic.main.activity_add_cities.viewLoading

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

    private fun showLoading(active: Boolean = true) {
        if (active) {
            viewLoading.visibility = View.VISIBLE

            listCities.visibility = View.GONE
        } else {
            viewLoading.visibility = View.GONE

            listCities.visibility = View.VISIBLE
        }
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

    override fun updateLoadingState(isLoading: Boolean) {
        showLoading(isLoading)
    }

    // AddCityItemDelegate
    override fun onTapAddCity(city: City) {
        viewModel.addCity(city)
    }
}