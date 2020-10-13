package com.albertcid.cardsgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCase
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCase

class MainViewModelImpl(
    private val playRoundUseCase: PlayRoundUseCase,
    private val restartGameUseCase: RestartGameUseCase
): MainViewModel, ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    override val viewState: LiveData<MainViewState>
        get() = _viewState

    override fun restartGame() {_viewState.value = MainViewState.ShowGameStatus(restartGameUseCase()) }

    override fun playRound() {
        _viewState.value = MainViewState.ShowGameStatus(playRoundUseCase())
    }
}