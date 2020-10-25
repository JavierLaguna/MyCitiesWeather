package com.lagunadev.mycitiesweather.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(idLayout: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(this.context).inflate(idLayout, this, attachToRoot)