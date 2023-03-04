package com.example.newsproject

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.newsproject.di.module.SharedPref
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    companion object {
        @JvmStatic
        lateinit var mApp: App
    }

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreate() {
        super.onCreate()
        mApp = this
    }
}