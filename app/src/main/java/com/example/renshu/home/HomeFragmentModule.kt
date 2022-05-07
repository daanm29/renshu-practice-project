package com.example.renshu.home

import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

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

        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun bindViewModel(homeViewModel: HomeViewModel): ViewModel

    }
}
