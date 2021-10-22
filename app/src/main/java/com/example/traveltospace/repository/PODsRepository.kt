package com.example.traveltospace.repository

import com.example.traveltospace.POD.PODDataObj

import com.example.traveltospace.POD.PODRetrofitImpl
import com.example.traveltospace.model.PODViewModel

class PODsRepository : PODRepositoryInterface {
    private var podViewModel:PODViewModel?=null

    override fun getPODFromInternet(date: String): List<PODDataObj> = podViewModel!!.getPODs()

}