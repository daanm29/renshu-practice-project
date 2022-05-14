package com.example.renshu.practice.list.add

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ListAddFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindListAddFragment(): ListAddFragment

    @Module
    interface Bindings {}
}
