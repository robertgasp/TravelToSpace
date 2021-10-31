package com.example.traveltospace.objectsPhoto.earthRetrofit

import com.google.gson.annotations.SerializedName

data class EarthServerResponseData(
    @field:SerializedName("caption") val caption: String?,
    @field:SerializedName("image") val image: String?,
    @field:SerializedName("date") val date: String?
)
