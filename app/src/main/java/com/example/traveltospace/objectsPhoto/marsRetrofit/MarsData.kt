package com.example.traveltospace.objectsPhoto.marsRetrofit

sealed class MarsData {
    data class Success(
        val marsServerResponseData: List<MarsServerResponseData>
    ) : MarsData()
    data class Error(val error: Throwable) : MarsData()
    data class Loading(val progress: Int?) : MarsData()
}