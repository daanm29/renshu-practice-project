package com.example.renshu.practice.list

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ListOverviewFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindListOverViewFragment(): ListOverviewFragment

    @Module
    interface Bindings {}

}
