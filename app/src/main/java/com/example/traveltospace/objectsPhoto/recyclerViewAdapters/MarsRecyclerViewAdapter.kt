package com.example.traveltospace.objectsPhoto.recyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.traveltospace.databinding.CardForRecyclerviewMarsBinding
import com.example.traveltospace.objectsPhoto.marsRetrofit.MarsServerResponseData

class MarsRecyclerViewAdapter(private var fragment: Fragment) :
    RecyclerView.Adapter<MarsRecyclerViewAdapter.MarsHolder>() {

    private var marsData:List<MarsServerResponseData> = listOf()

    fun setData(inputArrayData:ArrayList<MarsServerResponseData>){
        marsData = inputArrayData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsRecyclerViewAdapter.MarsHolder {
        val binding = CardForRecyclerviewMarsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MarsHolder(binding)
    }

    override fun onBindViewHolder(holder: MarsRecyclerViewAdapter.MarsHolder, position: Int) {
        holder.bind(marsData[position])
    }

    override fun getItemCount(): Int {
        return marsData.size
    }

    inner class MarsHolder(private val binding: CardForRecyclerviewMarsBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(singleMarsObject:MarsServerResponseData) = with(binding){
                imageMars.load(singleMarsObject.imageSrc){
                    lifecycle(fragment.viewLifecycleOwner)
                }
                name.text=singleMarsObject.name
                launchDate.text=singleMarsObject.launchDate
                landingDate.text=singleMarsObject.landingDate
                status.text=singleMarsObject.status
                earthDate.text=singleMarsObject.earthDate
            }
    }
}