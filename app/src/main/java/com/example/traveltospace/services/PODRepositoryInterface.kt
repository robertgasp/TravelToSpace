package com.example.traveltospace.services

import com.example.traveltospace.services.entities.PODDataObj

interface PODRepositoryInterface {
    fun getPODFromInternet():List<PODDataObj>
}