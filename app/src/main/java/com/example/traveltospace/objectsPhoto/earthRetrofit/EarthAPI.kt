package com.example.traveltospace.objectsPhoto.earthRetrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthAPI {

    @GET("api/images.php")
    fun getURLEarth(@Query("api_key") apiKey: String): Call<List<EarthServerResponseData>>
}