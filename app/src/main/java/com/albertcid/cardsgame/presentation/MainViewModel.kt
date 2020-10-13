package com.albertcid.cardsgame.presentation

import androidx.lifecycle.LiveData

interface MainViewModel {
    val viewState: LiveData<MainViewState>
    fun restartGame()
    fun playRound()
}