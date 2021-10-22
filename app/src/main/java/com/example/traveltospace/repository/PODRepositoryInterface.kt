package com.example.traveltospace.repository

import com.example.traveltospace.POD.PODDataObj

interface PODRepositoryInterface {
    fun getPODFromInternet(date:String):List<PODDataObj>
    //fun getPODFromInternet(apiKey:String, date:String):PODDataObj
}