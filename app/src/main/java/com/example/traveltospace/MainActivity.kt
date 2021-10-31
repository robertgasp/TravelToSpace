package com.example.traveltospace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.traveltospace.ui.PODFragment
import com.example.traveltospace.databinding.ActivityMainBinding
import com.example.traveltospace.ui.ObjectsPhoto
import com.example.traveltospace.ui.SettingsFragment

class MainActivity : AppCompatActivity(), BottomMenuClickInterface {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(App.instance.myCurrentTheme)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PODFragment.newInstance())
                .commit()
        }
        bottomNavigationTabs()
    }

    override fun selectBottomNavigationTab(selectedfragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, selectedfragment)
            .commitAllowingStateLoss()
    }

    private fun bottomNavigationTabs() {
        val bottomNavigationItemView = binding.bottomNavigationMenu
        bottomNavigationItemView.setOnItemSelectedListener {
            var selectedfragment: Fragment? = null
            when (it.itemId) {
                R.id.main_page -> {
                    selectedfragment = PODFragment()
                    selectBottomNavigationTab(selectedfragment)
                    return@setOnItemSelectedListener true
                }
                R.id.objects_photo -> {
                    selectedfragment = ObjectsPhoto()
                    selectBottomNavigationTab(selectedfragment)
                    return@setOnItemSelectedListener true
                }
                R.id.settings -> {
                    selectedfragment = SettingsFragment()
                    selectBottomNavigationTab(selectedfragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


    }

    fun changeTheme(theme: Int) {
        App.instance.myCurrentTheme = theme
        this.recreate()
    }
}