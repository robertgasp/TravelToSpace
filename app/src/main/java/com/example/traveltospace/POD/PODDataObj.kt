package com.example.traveltospace.POD

import android.os.Build
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.io.BufferedReader
import androidx.annotation.RequiresApi
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


}
