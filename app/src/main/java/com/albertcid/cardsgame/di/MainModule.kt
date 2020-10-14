package com.albertcid.cardsgame.di

import androidx.lifecycle.ViewModelProvider
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCaseImpl
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCaseImpl
import com.albertcid.cardsgame.presentation.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
interface MainModule {
    @Binds
    fun bindMainViewModelFactory(viewModelFactory: MainViewModelFactory): ViewModelProvider.NewInstanceFactory
}

//@Module
//object MainModule {
//    @Provides
//    @JvmStatic
//    fun provideIODispatcher(): ViewModelProvider.NewInstanceFactory {
//        return MainViewModelFactory(
//            PlayRoundUseCaseImpl(),
//            RestartGameUseCaseImpl()
//        )
//    }
//}