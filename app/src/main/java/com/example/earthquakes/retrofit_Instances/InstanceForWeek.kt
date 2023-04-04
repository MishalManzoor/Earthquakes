package com.example.earthquakes.retrofit_Instances

import com.example.earthquakes.interfaces.InterFaceForWeek
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceForWeek {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://earthquake.usgs.gov/earthquakes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        // here we will call the api
        .create(InterFaceForWeek::class.java)
}