package com.albertcid.cardsgame.presentation.state

import com.albertcid.cardsgame.domain.GameStatus

sealed class MainViewState {
    class ShowGameStatus(val gameStatus: GameStatus): MainViewState()
    object GameFinished: MainViewState()
}