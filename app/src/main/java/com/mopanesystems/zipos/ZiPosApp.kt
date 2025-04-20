package com.mopanesystems.zipos

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZiPosApp  : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize any libraries or SDKs here
    }
}