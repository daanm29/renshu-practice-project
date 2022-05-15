package com.example.renshu.practice.list.add

import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.practice.list.CustomListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ListAddFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindListAddFragment(): ListAddFragment

    @Module
    interface Bindings {

        @Binds
        @IntoMap
        @ViewModelKey(CustomListViewModel::class)
        fun bindViewModel(customListViewModel: CustomListViewModel): ViewModel
    }
}
