package com.albertcid.cardsgame

sealed class MainViewState {
    class ShowGameStatus(val gameStatus: GameStatus)
}