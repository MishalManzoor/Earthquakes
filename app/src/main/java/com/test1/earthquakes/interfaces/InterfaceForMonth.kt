package com.test1.earthquakes.interfaces

import com.test1.earthquakes.helperClass.HelperClass
import retrofit2.Response
import retrofit2.http.GET


interface InterfaceForMonth {

    @GET("fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2023-2-20&minfelt=50&minmagnitude=5")
    suspend fun getMonthlyUpdate() : Response<HelperClass>
}