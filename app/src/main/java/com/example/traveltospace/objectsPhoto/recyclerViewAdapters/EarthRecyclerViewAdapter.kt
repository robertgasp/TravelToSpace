package com.example.traveltospace.objectsPhoto.recyclerViewAdapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.bumptech.glide.Glide
import com.example.traveltospace.MainActivity
import com.example.traveltospace.R
import com.example.traveltospace.databinding.CardForRecyclerviewEarthBinding
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthServerResponseData
import com.example.traveltospace.ui.PODFragment
import com.squareup.picasso.Picasso

class EarthRecyclerViewAdapter(
    private var fragmnet: Fragment
) : RecyclerView.Adapter<EarthRecyclerViewAdapter.EarthHolder>() {

    private var earthData: List<EarthServerResponseData> = listOf()

    fun setData(earthArray: ArrayList<EarthServerResponseData>) {
        earthData = earthArray
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EarthRecyclerViewAdapter.EarthHolder {

        val binding = CardForRecyclerviewEarthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EarthHolder(binding)
    }

    override fun onBindViewHolder(holder: EarthRecyclerViewAdapter.EarthHolder, position: Int) {
        holder.bind(earthData[position])
    }

    override fun getItemCount(): Int {
        return earthData.size
    }

    inner class EarthHolder(private val binding: CardForRecyclerviewEarthBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            singleEarthPhoto: EarthServerResponseData
        ) = with(binding) {


            imageEarth.load("https://epic.gsfc.nasa.gov/epic-archive/jpg/${singleEarthPhoto.image}.jpg") {
                lifecycle(fragmnet.viewLifecycleOwner)
            }

            captionEarth.text = singleEarthPhoto.caption

            dateEarth.typeface = Typeface.createFromAsset(fragmnet.activity?.assets, "courbi.ttf")
            dateEarth.text = singleEarthPhoto.date

        }
    }
}