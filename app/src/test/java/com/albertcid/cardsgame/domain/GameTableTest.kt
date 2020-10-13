package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.GameStatus
import com.albertcid.cardsgame.domain.game.GameCardShuffler
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.game.GameTableImpl
import com.albertcid.cardsgame.domain.game.Player
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.nhaarman.mockitokotlin2.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class GameTableTest {

    private lateinit var sut: GameTable
    private val userPlayer = mock<Player>()
    private val opponentPlayer = mock<Player>()
    private val gameCardShuffler = mock<GameCardShuffler>()

    @Before
    fun setUp() {
        sut = GameTableImpl(userPlayer, opponentPlayer, gameCardShuffler)
    }

    @Test
    fun `When start game round is set to zero`() {
        val someCards = (spadesSuit + diamondsSuit).toMutableSet()
        given(gameCardShuffler.assignCards()).willReturn(someCards)
        sut.round = 3

        sut.startGame()

        assertTrue(sut.round == 0)
    }

    @Test
    fun `When start game each player should receive cards to play`() {
        val someCards = randomFullUserCardDeck
        given(gameCardShuffler.assignCards()).willReturn(someCards)
        given(userPlayer.cardPile).willReturn(spy(mutableListOf()))
        given(opponentPlayer.cardPile).willReturn(spy(mutableListOf()))

        sut.startGame()

        verify(userPlayer.cardPile).addAll(someCards)
        verify(opponentPlayer.cardPile).addAll(someCards)
    }

    @Test
    fun `When start game each player discard pile is set empty`() {
        val someCards = randomFullUserCardDeck
        given(gameCardShuffler.assignCards()).willReturn(someCards)
        given(userPlayer.discardPile).willReturn(spy(mutableSetOf()))
        given(opponentPlayer.discardPile).willReturn(spy(mutableSetOf()))

        sut.startGame()

        verify(userPlayer.discardPile).clear()
        verify(opponentPlayer.discardPile).clear()
    }


    @Test
    fun `Given round 0, When play round, round counter is increased by one`() {
        val expected = 1
        val randomCard = Card(CardValue.FOUR, CardSuit.SPADES)
        givenPlayedRound(randomCard, randomCard)

        sut.playRound()

        assertEquals(expected, sut.round)
    }

    @Test
    fun `Given round 2, When play round, round counter is increased by one`() {
        val expected = 2
        sut.startGame()
        sut.round = 1
        val randomCard = Card(CardValue.FOUR, CardSuit.SPADES)
        given(userPlayer.playCard()).willReturn(randomCard)
        given(opponentPlayer.playCard()).willReturn(randomCard)

        sut.playRound()

        assertEquals(expected, sut.round)
    }

    @Test
    fun `Given player ONE card higher than player TWO, player ONE gains the cards`() {
        val cardOne = Card(CardValue.FIVE, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.TWO, CardSuit.SPADES)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(userPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(opponentPlayer, never()).winRound(any())
    }

    @Test
    fun `Given player TWO card higher than player ONE, player TWO gains the cards`() {
        val someCards = randomFullUserCardDeck
        given(gameCardShuffler.assignCards()).willReturn(someCards)
        val cardOne = Card(CardValue.JOCKEY, CardSuit.DIAMONDS)
        val cardTwo = Card(CardValue.ACE, CardSuit.SPADES)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(opponentPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(userPlayer, never()).winRound(any())
    }

    @Test
    fun `Given player ONE card equal to player TWO card but suit most higher, player ONE wins`() {
        given(gameCardShuffler.generateSuitPriority()).willReturn(
            listOf(CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.CLUBS)
        )
        val cardOne = Card(CardValue.QUEEN, CardSuit.SPADES)
        val cardTwo = Card(CardValue.QUEEN, CardSuit.HEARTS)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(userPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(opponentPlayer, never()).winRound(any())
    }

    @Ignore
    @Test
    fun `Given user player lose round, should return correct Game Status is returned`() {
        givenPileResultsUserLose()
        val cardOne = Card(CardValue.TWO, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.KING, CardSuit.DIAMONDS)
        givenRoundResolved(cardOne, cardTwo)

        val expected = GameStatus(
            currentRound = 1,
            isUserWinnerOfRound = false,
            userCardPlayed = cardOne,
            opponentCardPlayed = cardTwo,
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 2
        )

        val actual = sut.getGameStatus()

        assertEquals(expected, actual)
    }

    @Ignore
    @Test
    fun `Given user player win round, should return correct Game Status is returned`() {
        givenPileResultsUserWin()
        sut.round = 1
        val cardOne = Card(CardValue.FIVE, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.TWO, CardSuit.SPADES)
        givenRoundResolved(cardOne, cardTwo)

        val expected = GameStatus(
            currentRound = 2,
            isUserWinnerOfRound = true,
            userCardPlayed = cardOne,
            opponentCardPlayed = cardTwo,
            totalUsersCardPile = 25,
            totalUsersDiscardPile = 2,
            totalOpponentDiscardPile = 0
        )

        val actual = sut.getGameStatus()

        assertEquals(expected, actual)

    }

    private fun givenPlayedRound(
        cardOne: Card,
        cardTwo: Card
    ) {
        given(userPlayer.playCard()).willReturn(cardOne)
        given(opponentPlayer.playCard()).willReturn(cardTwo)
        sut.startGame()
    }

    private fun givenRoundResolved(
        cardOne: Card,
        cardTwo: Card
    ) {
        given(userPlayer.playCard()).willReturn(cardOne)
        given(opponentPlayer.playCard()).willReturn(cardTwo)
        sut.playRound()
    }

    private fun givenPileResultsUserWin() {
        given(userPlayer.cardPile).willReturn(randomFullUserCardDeck.toMutableList())
        given(userPlayer.discardPile).willReturn(mutableSetOf())
        given(opponentPlayer.discardPile).willReturn(
            mutableSetOf(
                Card(CardValue.TWO, CardSuit.HEARTS),
                Card(CardValue.TWO, CardSuit.SPADES)
            )
        )
    }

    private fun givenPileResultsUserLose() {
        given(userPlayer.cardPile).willReturn(randomFullUserCardDeck.toMutableList())
        given(userPlayer.discardPile).willReturn(
            mutableSetOf(
                Card(CardValue.FIVE, CardSuit.HEARTS),
                Card(CardValue.KING, CardSuit.DIAMONDS)
            )
        )
        given(opponentPlayer.discardPile).willReturn(mutableSetOf())
    }
}