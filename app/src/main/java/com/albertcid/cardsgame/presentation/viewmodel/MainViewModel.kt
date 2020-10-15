package com.albertcid.cardsgame.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.albertcid.cardsgame.presentation.state.MainViewState

interface MainViewModel {
    val viewState: LiveData<MainViewState>
    fun restartGame()
    fun playRound()
}