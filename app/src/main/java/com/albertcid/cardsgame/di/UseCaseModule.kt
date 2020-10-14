package com.albertcid.cardsgame.di


import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCase
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCaseImpl
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCase
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCaseImpl
import dagger.Binds
import dagger.Module

//@Module
//interface UseCaseModule {
//    @Binds
//    fun bindPlayRoundUseCase(usecase: PlayRoundUseCaseImpl): PlayRoundUseCase
//
//    @Binds
//    fun bindRestartGameUseCase(usecase: RestartGameUseCaseImpl): RestartGameUseCase
//
//}