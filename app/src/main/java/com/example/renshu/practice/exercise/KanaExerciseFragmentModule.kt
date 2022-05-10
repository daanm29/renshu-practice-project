package com.example.renshu.practice.exercise

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.renshu.mvvm.ViewModelKey
import com.example.shupresentation.practice.exercise.ExerciseViewModel
import com.example.shupresentation.practice.exercise.ExerciseViewModelArguments
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface KanaExerciseFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class
        ]
    )
    fun bindKanaExerciseFragment(): KanaExerciseFragment

    @Module
    interface Bindings {

        @Binds
        @IntoMap
        @ViewModelKey(ExerciseViewModel::class)
        fun bindViewModel(exerciseViewModel: ExerciseViewModel): ViewModel

        @Binds
        fun bindFragment(kanaExerciseFragment: KanaExerciseFragment): Fragment

        companion object {

            @Provides
            fun bindFragment(fragment: Fragment): ExerciseViewModelArguments {
                return ExerciseViewModelArguments((KanaExerciseFragmentArgs.fromBundle(fragment.requireArguments()).alphabet))
            }
        }

    }
}
