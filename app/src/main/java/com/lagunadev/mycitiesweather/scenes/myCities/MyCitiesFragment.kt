package com.lagunadev.mycitiesweather.scenes.myCities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.fragment_my_cities.*

class MyCitiesFragment : Fragment(), MyCitiesViewModelDelegate {

    private val viewModel: MyCitiesViewModel by lazy {
        val factory = CustomViewModelFactory(activity!!.application, this)
        ViewModelProvider(this, factory).get(MyCitiesViewModel::class.java)
    }
    private val citiesAdapter: MyCitiesAdapter by lazy {
        val adapter = MyCitiesAdapter()
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize()

        listMyCities.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        listMyCities.adapter = citiesAdapter
    }

    // MyCitiesViewModelDelegate
    override fun onUpdateCities(cities: List<City>) {
        citiesAdapter.setCities(cities)
    }
}