package com.example.renshu.application

import com.example.renshu.application.di.DaggerRenshuApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RenshuApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerRenshuApplicationComponent.factory().create(this)
    }

}
