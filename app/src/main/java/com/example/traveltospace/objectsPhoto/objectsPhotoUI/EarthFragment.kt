package com.example.traveltospace.objectsPhoto.objectsPhotoUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.FragmentEarthBinding
import com.example.traveltospace.objectsPhoto.EarthViewModel
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthData
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthServerResponseData
import com.example.traveltospace.objectsPhoto.recyclerViewAdapters.EarthRecyclerViewAdapter


class EarthFragment : Fragment() {

    private val earthViewModel: EarthViewModel by lazy {
        ViewModelProviders.of(this).get(EarthViewModel::class.java)
    }

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!


    private var arrayListOfEarthObjects: MutableList<EarthServerResponseData> = mutableListOf()
    private var earthAdapter: EarthRecyclerViewAdapter? = null


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
                    arrayListOfEarthObjects.add(earthObject)
                }
                earthAdapter?.setData(arrayListOfEarthObjects as ArrayList<EarthServerResponseData>)
            }
        }
    }

    private fun initEarthRecyclerView(recyclerView: RecyclerView?) {
        val lm = LinearLayoutManager(context)
        recyclerView?.layoutManager = lm
        earthAdapter = EarthRecyclerViewAdapter(this)
        recyclerView?.adapter = earthAdapter
    }

}