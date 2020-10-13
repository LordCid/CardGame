package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.game.Player
import com.albertcid.cardsgame.domain.game.OponentPlayer
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
        sut = OponentPlayer()
    }

    @Test
    fun `Should play one card correctly`() {
        givenPileOfCards()
        val expectedPileCards = mutableListOf(
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        val expected = Card(CardValue.THREE, CardSuit.CLUBS)


        val actual = sut.playCard()

        assertEquals(expectedPileCards, sut.cardPile)
        assertEquals(expected, actual)
    }

    @Test
    fun `Should play ANOTHER card correctly`() {
        givenAnotherPileOfCards()
        val expectedPileCards = mutableListOf(
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        val expected = Card(CardValue.FOUR, CardSuit.CLUBS)

        val actual = sut.playCard()

        assertEquals(expectedPileCards, sut.cardPile)
        assertEquals(expected, actual)
    }

    @Test
    fun `When round winned should add cards to discard pile`() {
        val expected = mutableSetOf(
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )

        sut.winRound(Card(CardValue.FOUR, CardSuit.CLUBS), Card(CardValue.FIVE, CardSuit.CLUBS))

        assertEquals(expected, sut.discardPile)
    }

    private fun givenPileOfCards(){
        val pileCards = mutableSetOf(
            Card(CardValue.THREE, CardSuit.CLUBS),
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        sut.cardPile.addAll(pileCards)
    }

    private fun givenAnotherPileOfCards(){
        val pileCards = mutableSetOf(
            Card(CardValue.THREE, CardSuit.CLUBS),
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS)
        )
        sut.cardPile.addAll(pileCards)
        sut.playCard()
    }
}