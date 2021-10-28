package com.example.traveltospace.objectsPhoto.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.traveltospace.MainActivity
import com.example.traveltospace.databinding.CardForRecyclerviewEarthBinding
import com.example.traveltospace.databinding.FragmentEarthBinding
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthData
import com.example.traveltospace.objectsPhoto.earthRetrofit.EarthServerResponseData
import com.example.traveltospace.objectsPhoto.objectsPhotoUI.EarthFragment
import com.squareup.picasso.Picasso
import okhttp3.internal.notify

class RecyclerViewAdapterEarth(
    private var fragmnet: Fragment
) : RecyclerView.Adapter<RecyclerViewAdapterEarth.EarthHolder>() {

    private var earthData: List<EarthServerResponseData> = listOf()

    fun setEarth(earthArray: ArrayList<EarthServerResponseData>) {
        earthData = earthArray
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapterEarth.EarthHolder {

        val binding = CardForRecyclerviewEarthBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EarthHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterEarth.EarthHolder, position: Int) {
        holder.bind(earthData[position], holder.getBinding())
    }

    override fun getItemCount(): Int {
        return earthData.size
    }

    inner class EarthHolder(itemView: View, binding: CardForRecyclerviewEarthBinding) :
        RecyclerView.ViewHolder(itemView) {
        var binding2 = binding
            get() = field

        fun bind(
            singleEarthPhoto: EarthServerResponseData,
            field: CardForRecyclerviewEarthBinding
        ) = with(field) {

            //мой пример работающего запроса на фото Земли: https://epic.gsfc.nasa.gov/epic-archive/jpg/epic_1b_20211026041237.jpg

            Picasso
                .get()
                .load("https://epic.gsfc.nasa.gov/epic-archive/jpg/" + singleEarthPhoto.image + ".jpg")
                .fit()
                .into(imageEarth)

            captionEarth.text = singleEarthPhoto.caption
            dateEarth.text = singleEarthPhoto.caption
        }

        fun getBinding(): CardForRecyclerviewEarthBinding {
            val field2 = binding2
            return field2
        }
    }
}