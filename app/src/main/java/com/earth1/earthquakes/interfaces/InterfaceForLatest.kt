package com.earth1.earthquakes.interfaces

import com.earth1.earthquakes.helperClass.HelperClass
import retrofit2.Response
import retrofit2.http.GET

interface InterfaceForLatest {

    // past day 4.5+

    @GET("...")
    suspend fun getLatestUpdate() : Response<HelperClass>
}