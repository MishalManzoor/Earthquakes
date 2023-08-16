package com.test1.earthquakes.interfaces

import com.test1.earthquakes.helperClass.HelperClass
import retrofit2.Response
import retrofit2.http.GET

interface InterFaceForWeek {

     // 4.5+

    @GET("feed/v1.0/summary/4.5_week.geojson")
    suspend fun getWeeklyUpdate() : Response<HelperClass>
}