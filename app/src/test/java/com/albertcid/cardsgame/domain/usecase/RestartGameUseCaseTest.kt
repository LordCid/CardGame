package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before

import org.junit.Test

class RestartGameUseCaseTest {

    private lateinit var sut: RestartGameUseCase
    private val gameTable = mock<GameTable>()

    @Before
    fun setUp() {
        sut = RestartGameUseCaseImpl(gameTable)
    }

    @Test
    fun `When restart game, GameStatus Should return to inital state`() {
        val expected = GameStatus(
            currentRound = 0,
            isUserWinnerOfRound = false,
            userCardPlayed = Card(CardValue.TWO, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.KING, CardSuit.DIAMONDS),
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 0
        )
        given(gameTable.startGame()).willReturn( expected )

        val actual = sut.invoke()

        verify(gameTable).startGame()
        Assert.assertEquals(expected, actual)
    }
}