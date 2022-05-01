package com.example.renshu.main

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class
        ]
    )
    fun bindMainActivity(): MainActivity

    @Module
    interface Bindings {

        @Binds
        fun bindActivity(mainActivity: MainActivity): Activity
    }

}
