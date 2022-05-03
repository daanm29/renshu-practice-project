package com.example.renshu.settings.info

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AppInfoFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindAppInfoFragment(): AppInfoFragment

    @Module
    interface Bindings {

    }
}
