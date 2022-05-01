package com.example.renshu.application.di

import androidx.lifecycle.ViewModelProvider
import com.example.renshu.mvvm.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
