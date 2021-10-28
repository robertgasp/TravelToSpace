package com.example.traveltospace.objectsPhoto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveltospace.BuildConfig
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthData
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthRetrofitImpl
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthViewModel(
    private val liveDataForEarthToObserve: MutableLiveData<EarthData> = MutableLiveData(),
    private val earthRetrofitImpl: EarthRetrofitImpl = EarthRetrofitImpl()
) : ViewModel() {

    fun getEarthData(): LiveData<EarthData> {
        sendServerRequestEarth()
        return liveDataForEarthToObserve
    }


    private fun sendServerRequestEarth() {
        liveDataForEarthToObserve.value = EarthData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY

        earthRetrofitImpl.getEarthRetrofitImpl().getURLEarth(apiKey)
            .enqueue(object : Callback<List<EarthServerResponseData>> {
                override fun onResponse(
                    call: Call<List<EarthServerResponseData>>,
                    response: Response<List<EarthServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        EarthData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForEarthToObserve.value =
                                EarthData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForEarthToObserve.value =
                                EarthData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<EarthServerResponseData>>, t: Throwable) {
                    liveDataForEarthToObserve.value = EarthData.Error(t)
                }
            })
    }
}


