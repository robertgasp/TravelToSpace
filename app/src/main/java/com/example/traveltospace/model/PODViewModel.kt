package com.example.traveltospace.model

import androidx.lifecycle.*
import com.example.traveltospace.PODState
import com.example.traveltospace.services.PODRepositoryInterface
import com.example.traveltospace.services.entities.PODDataObj

class PODViewModel(private val repositoryInterface: PODRepositoryInterface) : ViewModel(),
    LifecycleObserver {

    private val myLiveData: MutableLiveData<PODState> = MutableLiveData()
    //private val myLiveData: MutableLiveData<List<PODDataObj>> = MutableLiveData()

    fun getMyLiveData(): LiveData<PODState> {
        return myLiveData
    }

    fun getPODs() {
        Thread {
            myLiveData.postValue(PODState.Success(repositoryInterface.getPODFromInternet()))
            //myLiveData.postValue(repositoryInterface.getPODFromInternet())
        }.start()
    }
}