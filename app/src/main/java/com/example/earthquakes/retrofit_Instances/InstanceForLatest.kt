package com.example.earthquakes.retrofit_Instances

import com.example.earthquakes.interfaces.InterfaceForLatest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceForLatest {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://earthquake.usgs.gov/earthquakes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        // here we will call the api
        .create(InterfaceForLatest::class.java)
}