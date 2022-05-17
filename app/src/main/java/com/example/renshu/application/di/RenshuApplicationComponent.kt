package com.example.renshu.application.di

import com.example.renshu.application.RenshuApplication
import com.example.renshu.main.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        RenshuApplicationModule::class,
        ViewModelModule::class,
        MainActivityModule::class,
    ]
)
interface RenshuApplicationComponent : AndroidInjector<RenshuApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<RenshuApplication>
}
