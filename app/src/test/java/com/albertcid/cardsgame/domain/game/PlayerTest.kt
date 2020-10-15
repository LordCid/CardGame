package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {

    private lateinit var sut: Player

    @Before
    fun setUp() {
        sut = PlayerImpl()
    }

    @Test
    fun `Should return card pile size`() {
        givenPileOfCards()
        val expected = 3

        val actual = sut.getCardPileSize()

        assertEquals(expected, actual)
    }

    @Test
    fun `Should return discard pile size`() {
        givenDiscardPile()
        val expected = 2

        val actual = sut.getDiscardPileSize()

        assertEquals(expected, actual)
    }

    @Test
    fun `Should play one card correctly`() {
        givenPileOfCards()
        val expected = Card(CardValue.THREE, CardSuit.CLUBS)


        val actual = sut.playCard()

        assertEquals(expected, actual)
    }

    @Test
    fun `Should play ANOTHER card correctly`() {
        givenAnotherPileOfCards()
        val expected = Card(CardValue.FOUR, CardSuit.CLUBS)

        val actual = sut.playCard()

        assertEquals(expected, actual)
    }

    @Test
    fun `When round winned should add cards to discard pile`() {
        val expectedSize = 2

        sut.winRound(Card(CardValue.FOUR, CardSuit.CLUBS), Card(CardValue.FIVE, CardSuit.CLUBS))

        assertEquals(expectedSize,  sut.getDiscardPileSize())
    }


    @Test
    fun `Should clear all decks`() {
        givenPileOfCards()
        givenDiscardPile()

        sut.clearDecks()

        assertEquals(0, sut.getCardPileSize())
        assertEquals(0, sut.getDiscardPileSize())
    }

    private fun givenPileOfCards(){
        val pileCards = mutableListOf(
            Card(CardValue.THREE, CardSuit.CLUBS),
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        sut.receiveCardPile(pileCards)
    }

    private fun givenAnotherPileOfCards(){
        val pileCards = mutableListOf(
            Card(CardValue.THREE, CardSuit.CLUBS),
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        sut.receiveCardPile(pileCards)
        sut.playCard()
    }

    private fun givenDiscardPile(){
        sut.winRound(Card(CardValue.FOUR, CardSuit.CLUBS), Card(CardValue.FIVE, CardSuit.CLUBS))
    }
}