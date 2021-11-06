package com.example.traveltospace.objectsPhoto.marsRetrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarsRetrofitImpl {

    private val baseMarsUrl = "https://api.nasa.gov/mars-photos/"

    fun getMarsRetrofit(): MarsAPI {
        val marsRetrofit = Retrofit.Builder()
            .baseUrl(baseMarsUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClientForMars(MarsInterceptor()))
            .build()
        return marsRetrofit.create(MarsAPI::class.java)
    }

    private fun createOkHttpClientForMars(interceptor: Interceptor): OkHttpClient {
        val marsHttpClient = OkHttpClient.Builder()
        marsHttpClient.addInterceptor(interceptor)
        marsHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return marsHttpClient.build()
    }

    inner class MarsInterceptor:Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}