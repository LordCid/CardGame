package com.albertcid.cardsgame.di

import androidx.lifecycle.ViewModelProvider
import com.albertcid.cardsgame.presentation.viewmodel.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface MainModule {
    @Binds
    fun bindMainViewModelFactory(viewModelFactory: MainViewModelFactory): ViewModelProvider.NewInstanceFactory
}
