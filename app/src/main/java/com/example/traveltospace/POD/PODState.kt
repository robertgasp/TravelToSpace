package com.example.traveltospace.POD

sealed class PODState{
    data class Success(val serverResponseData: PODDataObj): PODState()
    //data class Success(val podData:List<PODDataObj>): PODState()
    data class Error (val error:Throwable): PODState()
}
