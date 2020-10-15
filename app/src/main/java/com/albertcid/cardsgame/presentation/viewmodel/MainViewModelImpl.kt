package com.albertcid.cardsgame.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCase
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCase
import com.albertcid.cardsgame.presentation.state.MainViewState
import javax.inject.Inject

class MainViewModelImpl(
    private val playRoundUseCase: PlayRoundUseCase,
    private val restartGameUseCase: RestartGameUseCase
) : MainViewModel, ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    override val viewState: LiveData<MainViewState>
        get() = _viewState

    override fun restartGame() {
        _viewState.value =
            MainViewState.ShowGameStatus(restartGameUseCase())
    }

    override fun playRound() {
        val gameStatus = playRoundUseCase()
        _viewState.value =
            with(gameStatus) {
                if (isGameFinished) {
                    MainViewState.GameFinished(
                        this,
                        isPlayerWinner(totalUsersDiscardPile, totalOpponentDiscardPile)
                    )
                } else {
                    MainViewState.ShowGameStatus(this)
                }

            }

    }

    private fun isPlayerWinner(userPile: Int, opponentPile: Int) = userPile > opponentPile


}




class MainViewModelFactory @Inject constructor(
    private val playRoundUseCase: PlayRoundUseCase,
    private val restartGameUseCase: RestartGameUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModelImpl(playRoundUseCase, restartGameUseCase) as T
    }
}