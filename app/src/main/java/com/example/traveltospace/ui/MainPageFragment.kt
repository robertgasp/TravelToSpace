package com.example.traveltospace.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.FragmentMainPageBinding
import com.example.traveltospace.model.PODViewModel
import com.example.traveltospace.POD.PODDataObj
import com.example.traveltospace.ui.adapters.PictureOfTheDayAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class MainPageFragment : Fragment() {

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


        viewModel.getMyLiveData().observe(viewLifecycleOwner, {
            adapter?.setPOD(viewModel.getPODs())
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
        fun newInstance() = MainPageFragment()
    }

}