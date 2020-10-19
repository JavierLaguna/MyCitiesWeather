package com.lagunadev.mycitiesweather.scenes.noCities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.scenes.addCities.AddCitiesActivity
import kotlinx.android.synthetic.main.fragment_no_cities.*

class NoCitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_cities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        buttonAddCity.setOnClickListener { navigateToAddCities() }
    }

    private fun navigateToAddCities() {
        Intent(activity, AddCitiesActivity::class.java).apply {
            startActivity(this)
        }
    }
}