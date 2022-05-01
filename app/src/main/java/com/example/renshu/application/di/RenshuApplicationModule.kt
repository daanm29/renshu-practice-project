package com.example.renshu.application.di

import android.app.Application
import android.content.Context
import com.example.renshu.application.RenshuApplication
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        /* TODO: Add Layer Modules */
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
