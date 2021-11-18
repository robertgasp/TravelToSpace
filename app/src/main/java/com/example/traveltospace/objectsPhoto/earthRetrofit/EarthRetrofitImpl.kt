package com.example.traveltospace.objectsPhoto.earthRetrofit

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EarthRetrofitImpl {

    private val baseEarthUrl = "https://epic.gsfc.nasa.gov/"

    fun getEarthRetrofitImpl():EarthAPI{
        val earthRetrofit= Retrofit.Builder()
            .baseUrl(baseEarthUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClientForEarth(EarthInterceptor()))
            .build()
        return earthRetrofit.create(EarthAPI::class.java)
    }

    private fun createOkHttpClientForEarth(interceptor: Interceptor): OkHttpClient {
        val earthHttpClient = OkHttpClient.Builder()
        earthHttpClient.addInterceptor (interceptor)
        earthHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return earthHttpClient.build()
    }

    inner class EarthInterceptor:Interceptor{

        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}