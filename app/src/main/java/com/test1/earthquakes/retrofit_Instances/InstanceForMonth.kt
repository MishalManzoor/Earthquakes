package com.test1.earthquakes.retrofit_Instances

import com.test1.earthquakes.interfaces.InterfaceForMonth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InstanceForMonth {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://earthquake.usgs.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
            // here we will call the api
        .create(InterfaceForMonth::class.java)
}