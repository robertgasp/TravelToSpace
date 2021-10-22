package com.example.traveltospace.model

import androidx.lifecycle.*
import com.example.traveltospace.POD.PODAPIInterface
import com.example.traveltospace.POD.PODDataObj
import com.example.traveltospace.POD.PODRetrofitImpl
import com.example.traveltospace.POD.PODState
import com.example.traveltospace.repository.PODRepositoryInterface
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PODViewModel(private val repositoryInterface: PODRepositoryInterface) : ViewModel(),
    LifecycleObserver {

    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()

    private val myLiveData: MutableLiveData<List<PODDataObj>> = MutableLiveData()
    var tempPOD = PODDataObj()

    fun getMyLiveData(): LiveData<List<PODDataObj>> = myLiveData


    private fun sendServerRequest(date: String): PODDataObj {

        val apiKey = "3pMSJak1bU23UWTCMFnORvo4aU1WFbuv72FLNR4b"

        val dto = retrofitImpl.getRetrofitImpl().getPODFromInternet(apiKey, date)
            .execute().body()

        val pod = PODDataObj(
            title = dto?.title,
            description = dto?.description,
            date = dto?.toString(),
            pod = dto?.pod,
            podHD = dto?.podHD,
            mediaType = dto?.mediaType,
            copyright = dto?.copyright
        )

        return pod
    }


    fun getPODs(): List<PODDataObj> {
        val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())


        val tempArray = ArrayList<PODDataObj>()
        for (daysAgo in 0 downTo -3) {
            val rangeDate = Calendar.getInstance()
            rangeDate.add(Calendar.DATE, daysAgo)
            val date = dataFormat.format(Date(rangeDate.timeInMillis))
            Thread {
                tempPOD = sendServerRequest(date)
            }.start()

            val tempDODObj = tempPOD
            tempArray.add(tempDODObj)
        }
        myLiveData.postValue(tempArray)
        return tempArray
    }


}