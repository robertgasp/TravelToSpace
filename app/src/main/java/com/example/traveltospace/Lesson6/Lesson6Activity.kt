package com.example.traveltospace.Lesson6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.traveltospace.R

class Lesson6Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson6)

        supportFragmentManager.beginTransaction()
            .replace(R.id.lesson6_container, Lesson6Fragment())
            .commit()
    }
}