package com.example.renshu.settings

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface SettingsFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindSettingsFragment(): SettingsFragment

    @Module
    interface Bindings {

    }
}
