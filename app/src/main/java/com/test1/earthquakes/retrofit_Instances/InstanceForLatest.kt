package com.test1.earthquakes.retrofit_Instances

import com.test1.earthquakes.interfaces.InterfaceForLatest
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