package com.albertcid.cardsgame.domain

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
    private val gameStarter = mock<GameCardShuffler>()

    @Before
    fun setUp() {
        sut = GameTableImpl(playerOne, playerTwo, gameStarter)
    }

    @Test
    fun `Given game started, When round played, round counter is increased by one`() {
        val expected = 1
        val randomCard = Card(CardValue.FOUR, CardSuit.SPADES)
        given(playerOne.playCard()).willReturn(randomCard)
        given(playerTwo.playCard()).willReturn(randomCard)

        sut.playRound()

        assertEquals(expected, sut.round)
    }

    @Test
    fun `Given game second round, When round played, round counter is increased by one`() {
        val expected = 2
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
        given(playerOne.playCard()).willReturn(cardOne)
        given(playerTwo.playCard()).willReturn(cardTwo)

        sut.playRound()

        verify(playerOne, times(1)).winRound(cardOne, cardTwo)
        verify(playerTwo, never()).winRound(any())
    }

    @Test
    fun `Given player TWO card higher than player ONE, player TWO gains the cards`() {
        val cardOne = Card(CardValue.JOCKEY, CardSuit.DIAMONDS)
        val cardTwo = Card(CardValue.ACE, CardSuit.SPADES)
        given(playerOne.playCard()).willReturn(cardOne)
        given(playerTwo.playCard()).willReturn(cardTwo)

        sut.playRound()

        verify(playerTwo, times(1)).winRound(cardOne, cardTwo)
        verify(playerOne, never()).winRound(any())
    }

    @Test
    fun `Given player ONE card equal to player TWO card but suit most higher, player ONE wins`() {
        given(gameStarter.suitPriority).willReturn(
            listOf( CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.CLUBS)
        )
        val cardOne = Card(CardValue.QUEEN, CardSuit.SPADES)
        val cardTwo = Card(CardValue.QUEEN, CardSuit.HEARTS)
        given(playerOne.playCard()).willReturn(cardOne)
        given(playerTwo.playCard()).willReturn(cardTwo)

        sut.playRound()

        verify(playerOne, times(1)).winRound(cardOne, cardTwo)
        verify(playerTwo, never()).winRound(any())
    }

}