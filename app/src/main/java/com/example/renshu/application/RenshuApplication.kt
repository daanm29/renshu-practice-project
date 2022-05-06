package com.example.renshu.application

import android.content.Context
import android.content.SharedPreferences
import com.example.renshu.BuildConfig
import com.example.renshu.application.di.DaggerRenshuApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class RenshuApplication : DaggerApplication() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initInstallDate()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initInstallDate() {
        sharedPreferences = applicationContext.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())

        if (sharedPreferences.getString("install_date", "") == "") {
            sharedPreferences.edit().putString("install_date", simpleDateFormat.format(Date(System.currentTimeMillis()))).apply()
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerRenshuApplicationComponent.factory().create(this)
    }
}
