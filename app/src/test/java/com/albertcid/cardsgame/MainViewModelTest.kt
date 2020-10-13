package com.albertcid.cardsgame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.albertcid.cardsgame.domain.usecase.PlayRoundUseCase
import com.albertcid.cardsgame.domain.usecase.RestartGameUseCase
import com.albertcid.cardsgame.presentation.MainViewModel
import com.albertcid.cardsgame.presentation.MainViewModelImpl
import com.albertcid.cardsgame.presentation.MainViewState
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var sut: MainViewModel
    private val playRoundUseCase = mock<PlayRoundUseCase>()
    private val restartGameUseCase = mock<RestartGameUseCase>()

    private val observer = mock<Observer<MainViewState>>()
    private val captorScreenState = argumentCaptor<MainViewState>()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        sut = MainViewModelImpl(playRoundUseCase, restartGameUseCase)
    }


    @Test
    fun `When play card action should invoke play round Use case, and update view state`() {
        val resultGameStatus = GameStatus(
            currentRound = 1,
            isUserWinnerOfRound = false,
            userCardPlayed = Card(CardValue.TWO, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.KING, CardSuit.DIAMONDS),
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 2
        )
        given(playRoundUseCase.invoke()).willReturn(resultGameStatus)

        sut.viewState.observeForever(observer)
        sut.playRound()

        verify(observer).onChanged(captorScreenState.capture())
        val capturedState = captorScreenState.firstValue as MainViewState.ShowGameStatus
        assertEquals(resultGameStatus, capturedState.gameStatus)
    }

    @Test
    fun `When ANOTHER play card action should invoke play round Use case, and update view state`() {
        val resultGameStatus = GameStatus(
            currentRound = 2,
            isUserWinnerOfRound = false,
            userCardPlayed = Card(CardValue.TWO, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.KING, CardSuit.DIAMONDS),
            totalUsersCardPile = 24,
            totalUsersDiscardPile = 4,
            totalOpponentDiscardPile = 6
        )
        given(playRoundUseCase.invoke()).willReturn(resultGameStatus)

        sut.viewState.observeForever(observer)
        sut.playRound()

        verify(observer).onChanged(captorScreenState.capture())
        val capturedState = captorScreenState.firstValue as MainViewState.ShowGameStatus
        assertEquals(resultGameStatus, capturedState.gameStatus)
    }

    @Test
    fun `When restart game, GameStatus Should return to inital state`() {
        val resultGameStatus = GameStatus(
            currentRound = 0,
            isUserWinnerOfRound = false,
            userCardPlayed = Card(CardValue.TWO, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.KING, CardSuit.DIAMONDS),
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 0
        )
        given(restartGameUseCase.invoke()).willReturn(resultGameStatus)

        sut.viewState.observeForever(observer)
        sut.restartGame()

        verify(observer).onChanged(captorScreenState.capture())
        val capturedState = captorScreenState.firstValue as MainViewState.ShowGameStatus
        assertEquals(resultGameStatus, capturedState.gameStatus)
    }
}