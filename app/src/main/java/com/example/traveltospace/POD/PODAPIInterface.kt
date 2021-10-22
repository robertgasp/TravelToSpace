package com.example.traveltospace.POD

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PODAPIInterface{
@GET("planetary/apod")
fun getPODFromInternet(
    @Query("api_key") apiKey:String,
    @Query("date") date:String): Call<PODDataObj>
}