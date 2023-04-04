package com.example.earthquakes.interfaces

import com.example.earthquakes.helperClass.HelperClass
import retrofit2.Response
import retrofit2.http.GET


interface InterfaceForMonth {

    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2023-2-20&minfelt=50&minmagnitude=5

    @GET("fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2023-2-20&minfelt=50&minmagnitude=5")
    suspend fun getMonthlyUpdate() : Response<HelperClass>
}