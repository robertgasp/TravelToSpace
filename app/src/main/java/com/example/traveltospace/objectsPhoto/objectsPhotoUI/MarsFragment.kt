package com.example.traveltospace.objectsPhoto.objectsPhotoUI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.traveltospace.R
import com.example.traveltospace.databinding.FragmentMarsBinding


class MarsFragment : Fragment() {

    private var _binding:FragmentMarsBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater,container,false)
        return binding.root
    }

}