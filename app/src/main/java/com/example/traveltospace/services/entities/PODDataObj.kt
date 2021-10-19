package com.example.traveltospace.services.entities

import android.os.Build
import android.os.Parcelable
import com.example.traveltospace.MainActivity
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.io.BufferedReader
import androidx.annotation.RequiresApi
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection
import kotlin.collections.ArrayList

@Parcelize
data class PODDataObj(
    var title: String? = null,
    @SerializedName("explanation")
    var description: String? = null,
    var date: String? = null,
    @SerializedName("url")
    var pod: String? = null,
    @SerializedName("hdurl")
    var podHD: String? = null,
    @SerializedName("media_type")
    var mediaType: String? = null,
    var copyright: String? = null
) : Parcelable {

    companion object {
        var podsArray = ArrayList<PODDataObj>()
        var podHD: Boolean? = null
        var mainActivity = MainActivity()
        var startRangeDate: String? = null
        var endRangeDate: String? = null
        var someDaysAgo = -40


        fun getPODFromInternet(): List<PODDataObj> {
            val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            lateinit var urlConnection: HttpURLConnection

            try {
                val tempPODArray = ArrayList<PODDataObj>()
                for (daysAgo in 0 downTo someDaysAgo) {
                    val rangeDate = Calendar.getInstance()
                    rangeDate.add(Calendar.DATE, someDaysAgo)
                    val date = dataFormat.format(Date(rangeDate.timeInMillis))

                    val uri =
                        URL("https://api.nasa.gov/planetary/apod?api_key=3pMSJak1bU23UWTCMFnORvo4aU1WFbuv72FLNR4b&$date")

                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                        getLinesForOld(bufferedReader)
                    } else {
                        getLines(bufferedReader)
                    }

                    val jsonObject = JSONObject(lines)

                    val onePOD = PODDataObj("0", "0", "0", "0", "0", "0", "0")
                    onePOD.title = jsonObject.getString("title")
                    onePOD.description = jsonObject.getString("explanation")
                    onePOD.date = jsonObject.getString("date")
                    onePOD.mediaType = jsonObject.getString("media_type")
                    if (onePOD.mediaType == "image") {
                        onePOD.pod = jsonObject.getString("url")
                        onePOD.podHD = jsonObject.getString("hdurl")
                    }
                    onePOD.copyright = jsonObject.getString("copyright")

                    tempPODArray.add(onePOD)
                }
                podsArray = tempPODArray
                return podsArray
            } catch (e: Exception) {
                e.printStackTrace()
                return listOf()
            }
        }


        /*   fun getPODFromInternet(): List<PODDataObj> {
               val dataFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

               val rangeDate = Calendar.getInstance()
               rangeDate.add(Calendar.DATE, someDaysAgo)
               startRangeDate = dataFormat.format(Date(rangeDate.timeInMillis))

               endRangeDate = dataFormat.format(Date())

               val uri =
                   URL("https://api.nasa.gov/planetary/apod?api_key=3pMSJak1bU23UWTCMFnORvo4aU1WFbuv72FLNR4b&$startRangeDate&$endRangeDate")

               lateinit var urlConnection: HttpURLConnection

               try {
                   urlConnection = uri.openConnection() as HttpsURLConnection
                   urlConnection.requestMethod = "GET"
                   urlConnection.readTimeout = 10000
                   val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                   val lines = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                       getLinesForOld(bufferedReader)
                   } else {
                       getLines(bufferedReader)
                   }

                   val jsonObject = JSONObject(lines)
                   val jsonArray = jsonObject.getJSONArray("")
                   val tempPODArray = ArrayList<PODDataObj>()

                   for (i in 0..jsonArray.length() - 1) {
                       val onePOD = PODDataObj("0", "0", "0", "0", "0", "0", "0")
                       onePOD.title = jsonArray.getJSONObject(i).getString("title")
                       onePOD.description = jsonArray.getJSONObject(i).getString("explanation")
                       onePOD.date = jsonArray.getJSONObject(i).getString("date")
                       onePOD.mediaType = jsonArray.getJSONObject(i).getString("media_type")
                       if (onePOD.mediaType == "image") {
                           onePOD.pod = jsonArray.getJSONObject(i).getString("url")
                           onePOD.podHD = jsonArray.getJSONObject(i).getString("hdurl")
                       }
                       onePOD.copyright = jsonArray.getJSONObject(i).getString("copyright")
                       tempPODArray.add(onePOD)
                   }

                   podsArray = tempPODArray
                   return podsArray
               } catch (e: Exception) {
                   e.printStackTrace()
                   return listOf()
               }
           }*/

        private fun getLinesForOld(reader: BufferedReader): String {
            val rawData = StringBuilder(2048)
            var tempVariable: String?
            while (reader.readLine().also { tempVariable = it } != null) {
                rawData.append(tempVariable).append("\n")
            }
            reader.close()
            return rawData.toString()
        }

        @RequiresApi(Build.VERSION_CODES.N)
        private fun getLines(reader: BufferedReader): String {
            return reader.lines().collect(Collectors.joining("\n"))
        }
    }
}
