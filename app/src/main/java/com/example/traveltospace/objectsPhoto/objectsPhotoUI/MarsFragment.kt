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
import com.example.traveltospace.databinding.FragmentMarsBinding
import com.example.traveltospace.objectsPhoto.MarsViewModel
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsData
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsServerResponseData
import com.example.traveltospace.objectsPhoto.recyclerViewAdapters.MarsRecyclerViewAdapter


class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    private val marsViewModel: MarsViewModel by lazy {
        ViewModelProviders.of(this).get(MarsViewModel::class.java)
    }

    private var arrayListOfMarsObjects: MutableList<MarsServerResponseData> = mutableListOf()
    private var marsAdapter: MarsRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMarsRecyclerView(binding.marsRecyclerview)
        marsViewModel.getMarsData()
            .observe(viewLifecycleOwner, Observer<MarsData> { renderData(it) })
    }

    private fun renderData(data: MarsData?) {
        when (data) {
            is MarsData.Success -> {
                val serverResponseData = data.marsServerResponseData
                for (i in 0..serverResponseData.size - 1) {
                    val marsObject = MarsServerResponseData(
                        serverResponseData[i].name,
                        serverResponseData[i].launchDate,
                        serverResponseData[i].landingDate,
                        serverResponseData[i].status,
                        serverResponseData[i].earthDate,
                        serverResponseData[i].imageSrc
                    )
                    arrayListOfMarsObjects.add(marsObject)
                }
                marsAdapter?.setData(arrayListOfMarsObjects as ArrayList<MarsServerResponseData>)

            }
        }
    }


    private fun initMarsRecyclerView(marsRecyclerview: RecyclerView) {
        val lm=LinearLayoutManager(context)
        marsRecyclerview?.layoutManager = lm
        marsAdapter = MarsRecyclerViewAdapter(this)
        marsRecyclerview?.adapter = marsAdapter
    }

}