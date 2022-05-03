package com.example.renshu.settings

import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

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

        @Binds
        @IntoMap
        @ViewModelKey(SettingsViewModel::class)
        fun bindViewModel(settingsVIewModel: SettingsViewModel): ViewModel

    }
}
