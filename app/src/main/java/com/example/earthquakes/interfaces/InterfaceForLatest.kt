package com.example.earthquakes.interfaces

import com.example.earthquakes.helperClass.HelperClass
import retrofit2.Response
import retrofit2.http.GET

interface InterfaceForLatest {

    // past day 4.5+

    @GET("feed/v1.0/summary/4.5_day.geojson")
    suspend fun getLatestUpdate() : Response<HelperClass>
}