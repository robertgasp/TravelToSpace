package com.example.traveltospace.model

import androidx.lifecycle.*
import com.example.traveltospace.PODState
import com.example.traveltospace.services.PODRepositoryInterface
import com.example.traveltospace.services.entities.PODDataObj

class PODViewModel(private val repositoryInterface: PODRepositoryInterface) : ViewModel(),
    LifecycleObserver {

    private val myLiveData: MutableLiveData<PODState> = MutableLiveData()

    fun getMyLiveData()= myLiveData


    fun getPODs() {
        Thread {
            myLiveData.postValue(PODState.Success(repositoryInterface.getPODFromInternet()))
        }.start()
    }
}