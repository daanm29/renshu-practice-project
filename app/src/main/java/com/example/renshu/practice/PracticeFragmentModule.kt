package com.example.renshu.practice

import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.practice.PracticeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface PracticeFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindPracticeFragment(): PracticeFragment

    @Module
    interface Bindings {

        @Binds
        @IntoMap
        @ViewModelKey(PracticeViewModel::class)
        fun bindViewModel(practiceViewModel: PracticeViewModel) : ViewModel
    }
}
