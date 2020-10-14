package com.albertcid.cardsgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCase
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MainViewModelImpl(
    private val playRoundUseCase: PlayRoundUseCase,
    private val restartGameUseCase: RestartGameUseCase
): MainViewModel, ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    override val viewState: LiveData<MainViewState>
        get() = _viewState

    override fun restartGame() {_viewState.value = MainViewState.ShowGameStatus(restartGameUseCase()) }

    override fun playRound() { _viewState.value = MainViewState.ShowGameStatus(playRoundUseCase()) }
}

class MainViewModelFactory @Inject constructor(
    private val playRoundUseCase: PlayRoundUseCase,
    private val restartGameUseCase: RestartGameUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModelImpl(playRoundUseCase, restartGameUseCase) as T
    }
}