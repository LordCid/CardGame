package com.albertcid.cardsgame.presentation

import com.albertcid.cardsgame.GameStatus

sealed class MainViewState {
    class ShowGameStatus(val gameStatus: GameStatus)
}