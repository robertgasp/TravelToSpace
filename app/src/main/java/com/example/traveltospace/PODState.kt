package com.example.traveltospace

import com.example.traveltospace.services.entities.PODDataObj

sealed class PODState{
    data class Success(val podData:List<PODDataObj>):PODState()
    data class Error (val error:Throwable):PODState()
}
