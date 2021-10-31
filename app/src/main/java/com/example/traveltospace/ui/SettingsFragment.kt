package com.example.traveltospace.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.traveltospace.MainActivity
import com.example.traveltospace.R
import com.example.traveltospace.databinding.FragmentSettingsBinding
import com.example.traveltospace.databinding.MainFragmentBinding
import java.net.IDN


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var currentTheme: String? = "space_theme"
    private var isHD: Boolean = false
    private val CURRENT_THEME = "CURRENT_THEME"
    private val IS_HD = "IS_HD"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        currentTheme = initThemeSet()
        isHD = initHDSet()



        if (currentTheme == "space_theme") {
            binding.settingsChipSpaceTheme.isChecked = true
        } else
            if (currentTheme == "mars_theme") {
                binding.settingsChipMarsTheme.isChecked = true
            }

        binding.settingsChipHd.isChecked = isHD

        binding.settingsChipGroup.setOnCheckedChangeListener { group, checkedChip ->
            when (checkedChip) {
                R.id.settings_chip_space_theme -> {
                    binding.settingsChipSpaceTheme.isChecked = true
                    currentTheme = "space_theme"
                    saveSelectedThemeToDisk(currentTheme.toString())
                    (activity as? MainActivity)?.changeTheme(R.style.SpaceTheme)
                }
                R.id.settings_chip_mars_theme -> {
                    binding.settingsChipMarsTheme.isChecked = true
                    currentTheme = "mars_theme"
                    saveSelectedThemeToDisk(currentTheme.toString())
                    (activity as? MainActivity)?.changeTheme(R.style.MarsTheme)
                }
            }
        }


        binding.settingsChipHd.setOnCheckedChangeListener { compoundButton, hdChip ->
            if (binding.settingsChipHd.isChecked) {
                isHD = true
                saveHDSetToDisk(isHD)
            } else {
                isHD = false
                saveHDSetToDisk(isHD)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding.settingsMotionXml.transitionToEnd()
    }


    private fun initThemeSet(): String? {
        this.let {
            activity?.getPreferences(Context.MODE_PRIVATE)?.let { preferences ->
                currentTheme = preferences.getString(CURRENT_THEME, null)
            }
        }
        return currentTheme
    }

    private fun initHDSet(): Boolean {
        this.let {
            activity?.getPreferences(Context.MODE_PRIVATE)?.let { preferences ->
                isHD = preferences.getBoolean(IS_HD, false)
            }
        }
        return isHD
    }

    private fun saveSelectedThemeToDisk(theme: String) {
        this.let {
            val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
            val editor = preferences?.edit()
            editor?.putString(CURRENT_THEME, theme)?.apply()
        }
    }

    private fun saveHDSetToDisk(isHD: Boolean) {
        this.let {
            val preferences = activity?.getPreferences(Context.MODE_PRIVATE)
            val editor = preferences?.edit()
            editor?.putBoolean(IS_HD, isHD)?.apply()
        }
    }
}