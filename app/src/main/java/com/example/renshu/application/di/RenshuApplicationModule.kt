package com.example.renshu.application.di

import android.app.Application
import android.content.Context
import com.example.renshu.application.RenshuApplication
import com.example.shudata.di.RenshuDataModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        RenshuDataModule::class
    ]
)
interface RenshuApplicationModule {

    @Binds
    fun bindApplication(application: RenshuApplication): Application

    companion object {

        @Provides
        fun provideContext(application: RenshuApplication): Context = application.applicationContext
    }
}
