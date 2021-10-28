package com.example.traveltospace.objectsPhoto.objectsPhotoUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.R
import com.example.traveltospace.databinding.FragmentEarthBinding
import com.example.traveltospace.objectsPhoto.EarthViewModel
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthData
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthServerResponseData
import com.example.traveltospace.objectsPhoto.recyclerViewAdapters.RecyclerViewAdapterEarth
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.lang.reflect.Type


class EarthFragment : Fragment() {

    private val earthViewModel: EarthViewModel by lazy {
        ViewModelProviders.of(this).get(EarthViewModel::class.java)
    }

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    private var arrayListOfEarthObjects: ArrayList<EarthServerResponseData>? = null
    private var earthAdapter: RecyclerViewAdapterEarth? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEarthRecyclerView(binding.earthRecycler)
        earthViewModel.getEarthData()
            .observe(viewLifecycleOwner, Observer<EarthData> { renderData(it) })
        earthAdapter?.setEarth(arrayListOfEarthObjects!!)
    }


    private fun renderData(data: EarthData) {
        when (data) {
            is EarthData.Success -> {
                val serverResponseData = data.earthServerResponseData

                for (i in 0..serverResponseData.size - 1) {
                    val earthObject = EarthServerResponseData(
                        serverResponseData[i].caption,
                        serverResponseData[i].image,
                        serverResponseData[i].date
                    )
                    arrayListOfEarthObjects?.add(earthObject)
                }
            }
        }

    }

    private fun initEarthRecyclerView(recyclerView: RecyclerView?) {
        val lm = LinearLayoutManager(context)
        recyclerView?.layoutManager = lm
        earthAdapter = RecyclerViewAdapterEarth(this)
        recyclerView?.adapter = earthAdapter
    }

}