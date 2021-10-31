package com.example.traveltospace.objectsPhoto

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.traveltospace.objectsPhoto.objectsPhotoUI.EarthFragment
import com.example.traveltospace.objectsPhoto.objectsPhotoUI.MarsFragment

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> fragments[EARTH_FRAGMENT]
            1 -> fragments[MARS_FRAGMENT]
            else  ->fragments[EARTH_FRAGMENT]
        }
    }

}