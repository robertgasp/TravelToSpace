package com.example.traveltospace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveltospace.ui.MainPageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainPageFragment.newInstance())
                .commit()
        }
    }
}