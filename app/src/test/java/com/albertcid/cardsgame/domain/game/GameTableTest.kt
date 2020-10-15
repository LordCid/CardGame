package com.albertcid.cardsgame.domain.game


import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.albertcid.cardsgame.randomFullUserCardDeck
import com.albertcid.cardsgame.randomOpponentFullCardDeck
import com.nhaarman.mockitokotlin2.*
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GameTableTest {
    private lateinit var sut: GameTable
    private val userPlayer = mock<Player>()
    private val opponentPlayer = mock<Player>()
    private val cardShuffler = mock<CardShuffler>()

    @Before
    fun setUp() {
        sut = GameTableImpl(userPlayer, opponentPlayer, cardShuffler)
    }

    @Test
    fun `When start game round is set to zero`() {
        givenCurrentGameAtSomeRound(3)

        val actual = sut.startGame()

        assertTrue(actual.currentRound == 0)
    }



    @Test
    fun `When start game each player should receive cards to play`() {
        sut.startGame()

        verify(cardShuffler).assignCardsToPlayers(userPlayer, opponentPlayer)
    }

    @Test
    fun `When start game each player pilse cards is set empty`() {
        givenStartPileCards()
        givenStartDiscardCards()

        sut.startGame()

        verify(userPlayer).clearDecks()
        verify(opponentPlayer).clearDecks()
    }


    @Test
    fun `Given round 0, When play round, round counter is increased by one`() {
        val expected = 1
        givenPlayedRound(Card(), Card())

        val actual = sut.playRound()

        assertEquals(expected, actual.currentRound)
    }

    @Test
    fun `Given round 2, When play round, round counter is increased by one`() {
        val expected = 2
        givenCurrentGameAtSomeRound(1)

        val actual = sut.playRound()

        assertEquals(expected, actual.currentRound)
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
        val cardOne = Card(CardValue.JOCKEY, CardSuit.DIAMONDS)
        val cardTwo = Card(CardValue.ACE, CardSuit.SPADES)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(opponentPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(userPlayer, never()).winRound(any())
    }

    @Test
    fun `Given player ONE card equal to player TWO card but suit most higher, player ONE wins`() {
        given(cardShuffler.generateSuitPriority()).willReturn(
            listOf(CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.CLUBS)
        )
        val cardOne = Card(CardValue.QUEEN, CardSuit.SPADES)
        val cardTwo = Card(CardValue.QUEEN, CardSuit.HEARTS)
        givenPlayedRound(cardOne, cardTwo)
        sut.startGame()

        sut.playRound()

        verify(userPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(opponentPlayer, never()).winRound(any())
    }

    @Test
    fun `Given finish game conditions are matched correct status is returned`() {
        given(userPlayer.getDiscardPileSize()).willReturn(22)
        given(opponentPlayer.getDiscardPileSize()).willReturn(2)
        givenPlayedRound( Card(), Card())
        repeat(25){ sut.playRound() }

        val gameStatus = sut.playRound()

        assertTrue(gameStatus.isGameFinished)
    }

    private fun givenPlayedRound(
        cardOne: Card,
        cardTwo: Card
    ) {
        given(userPlayer.playCard()).willReturn(cardOne)
        given(opponentPlayer.playCard()).willReturn(cardTwo)
    }

    private fun givenStartDiscardCards() {
        given(userPlayer.discardPile).willReturn(spy(mutableSetOf()))
        given(opponentPlayer.discardPile).willReturn(spy(mutableSetOf()))
    }

    private fun givenStartPileCards() {
        given(userPlayer.cardPile).willReturn(spy(randomFullUserCardDeck.toMutableList()))
        given(opponentPlayer.cardPile).willReturn(spy(randomOpponentFullCardDeck.toMutableList()))
    }

    private fun givenCurrentGameAtSomeRound(round: Int) {
        sut.startGame()
        givenPlayedRound(Card(), Card())
        repeat(round) { sut.playRound() }
    }
}