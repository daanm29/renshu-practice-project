package com.example.renshu.practice

import dagger.Module
import dagger.android.ContributesAndroidInjector

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

    }
}
