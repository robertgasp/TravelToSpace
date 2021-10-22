package com.example.traveltospace.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltospace.databinding.PictureOfTheDayBinding
import com.example.traveltospace.POD.PODDataObj

class PictureOfTheDayAdapter(
    private var fragment: Fragment
) : RecyclerView.Adapter<PictureOfTheDayAdapter.PODHolder>() {

    private var podsData: List<PODDataObj> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setPOD(pods: List<PODDataObj>) {
        podsData = pods
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PODHolder {
        val binding = PictureOfTheDayBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PODHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: PODHolder, position: Int) {
        holder.bind(podsData[position], holder.getBinding())
    }

    override fun getItemCount(): Int {
        return podsData.size
    }

    inner class PODHolder(itemView: View, binding: PictureOfTheDayBinding) :
        RecyclerView.ViewHolder(itemView) {
        var binding2 = binding
            get() = field

        fun bind(singlePOD: PODDataObj, field: PictureOfTheDayBinding) = with(field) {
            title.text = singlePOD.title
            descriptionPodText.text = singlePOD.description
            date.text = singlePOD.date
            copyright.text = singlePOD.copyright
        }

        fun getBinding(): PictureOfTheDayBinding {
            val field2 = binding2
            return field2
        }
    }
}