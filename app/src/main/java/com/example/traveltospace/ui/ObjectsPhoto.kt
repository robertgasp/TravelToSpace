package com.example.traveltospace.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.traveltospace.R
import com.example.traveltospace.databinding.FragmentObjectsPhotoBinding
import com.example.traveltospace.objectsPhoto.ViewPagerAdapter
import com.example.traveltospace.objectsPhoto.objectsPhotoUI.MarsFragment

private const val EARTH = 0
private const val MARS = 1

class ObjectsPhoto : Fragment() {


    private var _binding: FragmentObjectsPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentObjectsPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        ViewPager.adapter = ViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(ViewPager)
        setHighLightedTab(EARTH)

        ViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setHighLightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun setHighLightedTab(position: Int) = with(binding) {
        val layoutInflater = LayoutInflater.from(context)

        tabLayout.getTabAt(EARTH)?.customView = null
        tabLayout.getTabAt(MARS)?.customView = null

        when (position) {
            EARTH -> setEarthHighLighted(layoutInflater)
            MARS -> setMarsHighLighted(layoutInflater)
            else -> setEarthHighLighted(layoutInflater)
        }
    }


    private fun setEarthHighLighted(layoutInflater: LayoutInflater) {
        val earth = layoutInflater.inflate(R.layout.objects_photo_custom_tab_earth, null)

        earth.findViewById<AppCompatTextView>(R.id.header_of_tab_earth)
            .setTextColor(
                ContextCompat.getColor(
                    requireActivity(), R.color.feature_1_space_theme_accent_tab
                )
            )
        binding.tabLayout.getTabAt(EARTH)?.customView = earth
        binding.tabLayout.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.objects_photo_custom_tab_mars, null)
    }

    private fun setMarsHighLighted(layoutInflater: LayoutInflater?) {
        val mars = layoutInflater?.inflate(R.layout.objects_photo_custom_tab_mars, null)

        mars?.findViewById<AppCompatTextView>(R.id.header_of_tab_mars)
            ?.setTextColor(
                ContextCompat.getColor(requireActivity(), R.color.feature_1_space_theme_accent_tab)
            )
        binding.tabLayout.getTabAt(EARTH)?.customView = layoutInflater?.inflate(R.layout.objects_photo_custom_tab_earth, null)
        binding.tabLayout.getTabAt(MARS)?.customView = mars
    }

}