package com.lagunadev.mycitiesweather.scenes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.scenes.myCities.MyCitiesFragment
import com.lagunadev.mycitiesweather.scenes.noCities.NoCitiesFragment
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory

class MainActivity : AppCompatActivity(), MainActivityViewModelDelegate {

    private val viewModel: MainActivityViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        viewModel.delegate = this
        viewModel.initialize()
    }

    private fun showMyCities() {
        supportFragmentManager.beginTransaction().replace(R.id.content, MyCitiesFragment())
            .commitNow()
    }

    private fun showNoCities() {
        supportFragmentManager.beginTransaction().replace(R.id.content, NoCitiesFragment())
            .commitNow()
    }

    // MainActivityViewModelDelegate
    override fun citiesFound() {
        showMyCities()
    }

    override fun noCitiesFound() {
        showNoCities()
    }
}