package com.lagunadev.mycitiesweather.scenes.cityWeather

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.models.City
import com.lagunadev.mycitiesweather.scenes.weatherList.WeatherItemViewModel
import com.lagunadev.mycitiesweather.scenes.weatherList.WeatherListFragment
import com.lagunadev.mycitiesweather.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_city_weather.*
import kotlinx.android.synthetic.main.dialog_loading.view.*

class CityWeatherActivity : AppCompatActivity(), CityWeatherViewModelDelegate {

    companion object {
        const val CITY_OBJECT = "CITY_OBJECT"
    }

    private val weatherListFragment = WeatherListFragment()
    private lateinit var city: City
    private val viewModel: CityWeatherViewModel by lazy {
        val factory = CustomViewModelFactory(application, this)
        ViewModelProvider(this, factory).get(CityWeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        getIntentArguments()
        addListWeathers()
        setListeners()
        customizeLoading()
    }

    private fun initialize() {
        setContentView(R.layout.activity_city_weather)

        setSupportActionBar(findViewById(R.id.cityToolbar))

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        viewModel.delegate = this
    }

    private fun getIntentArguments() {
        intent?.let {
            city = it.getSerializableExtra(CityWeatherActivity.CITY_OBJECT) as City
            viewModel.initialize(city)
            setCityData()
        } ?: finish()
    }

    private fun setCityData() {
        this.supportActionBar?.title = city.title
    }

    private fun addListWeathers() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.weatherListContainer, weatherListFragment)
            .commitNow()
    }

    private fun setListeners() {
        buttonRefresh.setOnClickListener { viewModel.getCityWeather(city) }
    }

    private fun showLoading(active: Boolean = true) {
        if (active) {
            viewLoading.visibility = View.VISIBLE

            detailsContainer.visibility = View.GONE
        } else {
            viewLoading.visibility = View.GONE

            detailsContainer.visibility = View.VISIBLE
        }
    }

    private fun customizeLoading() {
        viewLoading.labelLoadingMessage.setTextColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.white
            )
        )
    }

    // CityWeatherViewModelDelegate
    override fun updateTodayWeather(todayWeather: TodayWeatherViewModel) {
        val resourceId: Int = resources.getIdentifier(
            todayWeather.stateAbbrUrl, "drawable", packageName
        )

        Glide.with(applicationContext).load(resourceId).into(imageCurrentWeather)

        with(todayWeather) {
            labelTemp.text = tempFormatted
            labelState.text = state
            labelDate.text = dateFormatted
            labelWind.text = windSpeedFormatted
            labelAirPreassure.text = airPressureFormatted
            labelHumidity.text = humidityFormatted
            labelTempMin.text = minTempFormatted
            labelTempMax.text = maxTempFormatted
        }
    }

    override fun updateNextDaysWeather(nextDaysWeather: List<WeatherItemViewModel>) {
        weatherListFragment.setWeather(nextDaysWeather)
    }

    override fun showError() {
        Snackbar.make(container, R.string.error_default, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateLoadingState(isLoading: Boolean) {
        showLoading(isLoading)
    }
}