package com.example.traveltospace.objectsPhoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveltospace.BuildConfig
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsData
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsRetrofitImpl
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsServerResponseData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.text.SimpleDateFormat
import java.util.*


class MarsViewModel(
    private val liveDataForMarsToObserve: MutableLiveData<MarsData> = MutableLiveData(),
    private val marsRetrofitImpl: MarsRetrofitImpl = MarsRetrofitImpl()
) : ViewModel() {

    fun getMarsData(): LiveData<MarsData> {
        sendServerRequestMars()
        return liveDataForMarsToObserve
    }


    private val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private val currentDate = Calendar.getInstance()
    private val date = dataFormat.format(Date(currentDate.timeInMillis))
    val newDate = "2021-10-28"

    private fun sendServerRequestMars() {
        liveDataForMarsToObserve.value = MarsData.Loading(null)
        val apikey: String = BuildConfig.NASA_API_KEY

        marsRetrofitImpl.getMarsRetrofit().getURLMars(newDate, apikey)
            .enqueue(object : Callback<List<MarsServerResponseData>> {
                override fun onResponse(
                    call: Call<List<MarsServerResponseData>>,
                    response: Response<List<MarsServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForMarsToObserve.value = MarsData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForMarsToObserve.value =
                                MarsData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForMarsToObserve.value = MarsData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<MarsServerResponseData>>, t: Throwable) {
                    liveDataForMarsToObserve.value = MarsData.Error(t)
                }

            })
    }
}