package com.example.renshu.home

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface HomeFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindHomeFragment(): HomeFragment

    @Module
    interface Bindings {

    }
}
