package com.example.traveltospace.POD.retrofit

sealed class PODData {
    data class Success(val serverResponseData: PODServerResponseData) : PODData()
    data class Error(val error: Throwable) : PODData()
    data class Loading(val progress: Int?) : PODData()
}