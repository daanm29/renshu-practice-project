package com.example.renshu.main

import android.app.Activity
import com.example.renshu.home.HomeFragmentModule
import com.example.renshu.practice.PracticeFragmentModule
import com.example.renshu.settings.SettingsFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
            HomeFragmentModule::class,
            PracticeFragmentModule::class,
            SettingsFragmentModule::class,
        ]
    )
    fun bindMainActivity(): MainActivity

    @Module
    interface Bindings {

        @Binds
        fun bindActivity(mainActivity: MainActivity): Activity
    }

}