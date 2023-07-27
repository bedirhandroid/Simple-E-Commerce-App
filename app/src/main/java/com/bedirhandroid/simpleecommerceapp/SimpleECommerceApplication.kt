package com.bedirhandroid.simpleecommerceapp

import android.app.Application
import com.bedirhandroid.simpleecommerceapp.local.LocalDataManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SimpleECommerceApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startLocalDataManager()
    }

    private fun startLocalDataManager() {
        LocalDataManager.init(applicationContext)
    }
}