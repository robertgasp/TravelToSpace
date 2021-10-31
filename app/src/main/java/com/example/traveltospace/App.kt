package com.example.traveltospace

import android.app.Application

class App : Application() {


    var myCurrentTheme = R.style.SpaceTheme

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}