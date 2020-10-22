package com.lagunadev.mycitiesweather.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "cities_table")
data class City(

    @PrimaryKey
    @field:SerializedName("woeid")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("location_type")
    val locationType: String? = null,

    @field:SerializedName("latt_long")
    val lattLong: String? = null

) : Serializable
