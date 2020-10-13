package com.albertcid.cardsgame

import androidx.lifecycle.LiveData

interface MainViewModel {
    val viewState: LiveData<MainViewState>
    fun restartGame()
    fun playRound()
}