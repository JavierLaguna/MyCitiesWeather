package com.lagunadev.mycitiesweather.scenes.addCities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_add_cities.*

class AddCitiesActivity : AppCompatActivity(), AddCitiesViewModelDelegate {

    private val viewModel: AddCitiesViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(AddCitiesViewModel::class.java)
    }
    private val citiesAdapter: AddCitiesAdapter by lazy {
        val adapter = AddCitiesAdapter()
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cities)

        initialize()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCities("Ma")
    }

    private fun initialize() {
        viewModel.delegate = this

        listCities.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listCities.adapter = citiesAdapter
    }

    // AddCitiesViewModelDelegate
    override fun onUpdateCities(cities: List<City>) {
        citiesAdapter.setCities(cities)
    }
}