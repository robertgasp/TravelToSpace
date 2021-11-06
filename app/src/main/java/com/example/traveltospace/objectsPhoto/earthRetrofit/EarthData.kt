package com.example.traveltospace.objectsPhoto.earthRetrofit

sealed class EarthData {
    data class Success(val earthServerResponseData: List<EarthServerResponseData>) : EarthData()
    data class Error(val error: Throwable) : EarthData()
    data class Loading(val progress: Int?) : EarthData()
}
