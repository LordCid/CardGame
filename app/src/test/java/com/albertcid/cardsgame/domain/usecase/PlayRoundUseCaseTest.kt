package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before

import org.junit.Test

class PlayRoundUseCaseTest {

    private lateinit var sut : PlayRoundUseCase
    private val gameTable = mock<GameTable>()

    @Before
    fun setUp() {
        sut = PlayRoundUseCaseImpl(gameTable)
    }

    @Test
    fun `Given use player lose round, When invoked, correct Game Status is returned`() {
        val expected = GameStatus(
            currentRound = 1,
            isUserWinnerOfRound = false,
            isGameFinished = false,
            userCardPlayed = Card(CardValue.TWO, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.KING, CardSuit.DIAMONDS),
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 2
        )

        given(gameTable.playRound()).willReturn( expected )

        val actual = sut.invoke()

        verify(gameTable).playRound()
        assertEquals(expected, actual)
    }

    @Test
    fun `Given use player win round, When invoked, correct Game Status is returned`() {
        val expected = GameStatus(
            currentRound = 2,
            isUserWinnerOfRound = true,
            isGameFinished = false,
            userCardPlayed = Card(CardValue.FIVE, CardSuit.HEARTS),
            opponentCardPlayed = Card(CardValue.TWO, CardSuit.SPADES),
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 2,
            totalOpponentDiscardPile = 0
        )

        given(gameTable.playRound()).willReturn( expected )

        val actual = sut.invoke()

        verify(gameTable).playRound()
        assertEquals(expected, actual)
    }

}