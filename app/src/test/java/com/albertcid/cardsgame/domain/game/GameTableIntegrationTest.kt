package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.albertcid.cardsgame.randomFullUserCardDeck
import com.albertcid.cardsgame.randomOpponentFullCardDeck
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameTableIntegrationTest {

    private lateinit var sut: GameTable

    private val userPlayer = PlayerImpl()
    private val opponentPlayer = PlayerImpl()
    private val cardShuffler = mock<CardShuffler>()

    @Before
    fun setUp() {

        sut = GameTableImpl(userPlayer, opponentPlayer, cardShuffler)
    }

    @Test
    fun `Given user player lose first round, should return correct Game Status is returned`() {
        givenFirstRound()
        val cardOne = Card(CardValue.TWO, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.KING, CardSuit.DIAMONDS)

        val expected = GameStatus(
            currentRound = 1,
            isUserWinnerOfRound = false,
            userCardPlayed = cardOne,
            opponentCardPlayed = cardTwo,
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 2
        )

        val actual = sut.playRound()

        Assert.assertEquals(expected, actual)
    }


    @Test
    fun `Given user player win second round, should return correct Game Status is returned`() {
        givenSecondRound()
        val cardOne = Card(CardValue.THREE, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.TWO, CardSuit.DIAMONDS)
        val expected = GameStatus(
            currentRound = 2,
            isUserWinnerOfRound = true,
            userCardPlayed = cardOne,
            opponentCardPlayed = cardTwo,
            totalUsersCardPile = 24,
            totalUsersDiscardPile = 2,
            totalOpponentDiscardPile = 2
        )

        val actual = sut.playRound()

        assertEquals(expected, actual)
    }

    private fun givenFirstRound(){
        given(cardShuffler.assignCards()).willReturn(mutableSetOf())
        sut.startGame()
        userPlayer.cardPile.addAll(randomFullUserCardDeck)
        opponentPlayer.cardPile.addAll(randomOpponentFullCardDeck)
    }

    private fun givenSecondRound(){
        given(cardShuffler.assignCards()).willReturn(mutableSetOf())
        sut.startGame()
        userPlayer.cardPile.addAll(randomFullUserCardDeck)
        opponentPlayer.cardPile.addAll(randomOpponentFullCardDeck)
        sut.playRound()
    }

//    private fun givenPileResultsUserWin() {
//        given(userPlayer.cardPile).willReturn(randomFullUserCardDeck.toMutableList())
//        given(userPlayer.discardPile).willReturn(mutableSetOf())
//        given(opponentPlayer.discardPile).willReturn(
//            mutableSetOf(
//                Card(CardValue.TWO, CardSuit.HEARTS),
//                Card(CardValue.TWO, CardSuit.SPADES)
//            )
//        )
//    }
//
//    private fun givenPileResultsUserLose() {
//        given(userPlayer.cardPile).willReturn(randomFullUserCardDeck.toMutableList())
//        given(userPlayer.discardPile).willReturn(
//            mutableSetOf(
//                Card(CardValue.FIVE, CardSuit.HEARTS),
//                Card(CardValue.KING, CardSuit.DIAMONDS)
//            )
//        )
//        given(opponentPlayer.discardPile).willReturn(mutableSetOf())
//    }
}