package com.lagunadev.mycitiesweather.scenes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lagunadev.mycitiesweather.R
import com.lagunadev.mycitiesweather.scenes.noCities.NoCitiesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.content, NoCitiesFragment())
            .commitNow()
    }
}