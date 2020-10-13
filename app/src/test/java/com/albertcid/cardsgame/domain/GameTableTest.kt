package com.albertcid.cardsgame.domain

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
import org.junit.Test

class GameTableTest {

    private lateinit var sut: GameTable
    private val playerOne = mock<Player>()
    private val playerTwo = mock<Player>()
    private val gameCardShuffler = mock<GameCardShuffler>()

    @Before
    fun setUp() {
        sut = GameTableImpl(playerOne, playerTwo, gameCardShuffler)
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
    fun `When start game each player discard pile is set empty and should receive cards to play`() {
        val someCards = randomFullPlayersDeck
        given(gameCardShuffler.assignCards()).willReturn(someCards)
        given(playerOne.cardPile).willReturn(spy(mutableListOf()))
        given(playerTwo.cardPile).willReturn(spy(mutableListOf()))

        sut.startGame()

        verify(playerOne.cardPile).addAll(someCards)
        verify(playerTwo.cardPile).addAll(someCards)
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
        given(playerOne.playCard()).willReturn(randomCard)
        given(playerTwo.playCard()).willReturn(randomCard)

        sut.playRound()

        assertEquals(expected, sut.round)
    }

    @Test
    fun `Given player ONE card higher than player TWO, player ONE gains the cards`() {
        val cardOne = Card(CardValue.FIVE, CardSuit.HEARTS)
        val cardTwo = Card(CardValue.TWO, CardSuit.SPADES)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(playerOne, times(1)).winRound(cardOne, cardTwo)
        verify(playerTwo, never()).winRound(any())
    }

    @Test
    fun `Given player TWO card higher than player ONE, player TWO gains the cards`() {
        val cardOne = Card(CardValue.JOCKEY, CardSuit.DIAMONDS)
        val cardTwo = Card(CardValue.ACE, CardSuit.SPADES)
        givenPlayedRound(cardOne, cardTwo)

        sut.playRound()

        verify(playerTwo, times(1)).winRound(cardOne, cardTwo)
        verify(playerOne, never()).winRound(any())
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

        verify(playerOne, times(1)).winRound(cardOne, cardTwo)
        verify(playerTwo, never()).winRound(any())
    }

    private fun givenPlayedRound(
        cardOne: Card,
        cardTwo: Card
    ) {
        given(playerOne.playCard()).willReturn(cardOne)
        given(playerTwo.playCard()).willReturn(cardTwo)
        sut.startGame()
    }

}