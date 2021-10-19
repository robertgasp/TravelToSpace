package com.example.traveltospace.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.PODState
import com.example.traveltospace.R
import com.example.traveltospace.databinding.FragmentMainPageBinding
import com.example.traveltospace.model.PODViewModel
import com.example.traveltospace.services.entities.PODDataObj
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainPage : Fragment() {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private var recyclerView: RecyclerView? = null
    private var adapter: PictureOfTheDayAdapter? = null
    private val viewModel: PODViewModel by viewModel()
    //private lateinit var viewModel:PODViewModel //by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewField
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(recyclerView)

        viewModel.getMyLiveData().observe(requireActivity(), {
            adapter?.setPOD(PODDataObj.getPODFromInternet())
        })
        viewModel.getPODs()
    }

    private fun initRecyclerView(recyclerView: RecyclerView?) {
        val lm = LinearLayoutManager(context)
        recyclerView?.layoutManager = lm
        adapter = PictureOfTheDayAdapter(this)
        recyclerView?.adapter = adapter
    }


    companion object {
        fun newInstance() = MainPage()
    }

}