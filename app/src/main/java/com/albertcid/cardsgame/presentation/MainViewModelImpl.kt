package com.albertcid.cardsgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModelImpl: MainViewModel, ViewModel() {
    private val _viewState = MutableLiveData<MainViewState>()
    override val viewState: LiveData<MainViewState>
        get() = _viewState

    override fun restartGame() {
        TODO("Not yet implemented")
    }

    override fun playRound() {
        TODO("Not yet implemented")
    }
}