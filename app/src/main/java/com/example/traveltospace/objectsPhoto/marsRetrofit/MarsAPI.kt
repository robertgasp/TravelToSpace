package com.example.traveltospace.objectsPhoto.marsRetrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsAPI {

    @GET("api/v1/rovers/curiosity/photos")

    fun getURLMars(
        @Query("earth_date") earthDate:String,
        @Query("api_key") apiKey:String ): Call<List<MarsServerResponseData>>
}