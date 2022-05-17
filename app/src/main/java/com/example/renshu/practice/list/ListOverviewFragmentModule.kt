package com.example.renshu.practice.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.practice.list.ListOverviewViewModel
import com.example.shupresentation.practice.list.ListOverviewViewModelArguments
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ListOverviewFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindListOverViewFragment(): ListOverviewFragment

    @Module
    interface Bindings {

        @Binds
        @IntoMap
        @ViewModelKey(ListOverviewViewModel::class)
        fun bindViewModel(listOverviewViewModel: ListOverviewViewModel): ViewModel

        @Binds
        fun bindFragment(listOverviewFragment: ListOverviewFragment): Fragment

        companion object {

            @Provides
            fun bindFragment(fragment: Fragment): ListOverviewViewModelArguments {
                return ListOverviewViewModelArguments(ListOverviewFragmentArgs.fromBundle(fragment.requireArguments()).title)
            }
        }
    }

}
