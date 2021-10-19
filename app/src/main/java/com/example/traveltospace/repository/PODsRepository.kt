package com.example.traveltospace.repository

import com.example.traveltospace.services.PODRepositoryInterface
import com.example.traveltospace.services.entities.PODDataObj

class PODsRepository : PODRepositoryInterface {
    override fun getPODFromInternet(): List<PODDataObj> {
        return PODDataObj.getPODFromInternet()
    }
}