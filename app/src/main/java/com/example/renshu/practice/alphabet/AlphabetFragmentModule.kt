package com.example.renshu.practice.alphabet

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.practice.alphabet.AlphabetViewModel
import com.example.shupresentation.practice.alphabet.AlphabetViewModelArguments
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface AlphabetFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
        ]
    )
    fun bindAlphabetFragment(): AlphabetFragment

    @Module
    interface Bindings {

        @Binds
        @IntoMap
        @ViewModelKey(AlphabetViewModel::class)
        fun bindViewModel(alphabetViewModel: AlphabetViewModel): ViewModel

        @Binds
        fun bindFragment(alphabetFragment: AlphabetFragment): Fragment

        companion object {

            @Provides
            fun bindFragment(fragment: Fragment): AlphabetViewModelArguments {
                return AlphabetViewModelArguments(AlphabetFragmentArgs.fromBundle(fragment.requireArguments()).alphabet)
            }
        }
    }
}
