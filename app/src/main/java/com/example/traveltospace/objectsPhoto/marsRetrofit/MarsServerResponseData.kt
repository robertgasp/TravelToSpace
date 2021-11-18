package com.example.traveltospace.objectsPhoto.marsRetrofit

import com.google.gson.annotations.SerializedName

data class MarsServerResponseData(
    @field:SerializedName("name") val name:String,
    @field:SerializedName("launch_date") val launchDate:String,
    @field:SerializedName("landing_date") val landingDate:String,
    @field:SerializedName("status") val status:String,
    @field:SerializedName("earth_date") val earthDate:String,
    @field:SerializedName("img_src") val imageSrc:String
)
