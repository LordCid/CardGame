package com.albertcid.cardsgame.domain

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CardDeckFactoryTest {

    private lateinit var sut: CardDeckFactory



    @Before
    fun setUp() {
        sut = CardDeckFactoryImpl()
    }

    @Test
    fun `Should build correct cardDeck for Spades suit`() {
        val expected = listOf(
            Card(CardValue.TWO, CardSuit.SPADES),
            Card(CardValue.THREE, CardSuit.SPADES),
            Card(CardValue.FOUR, CardSuit.SPADES),
            Card(CardValue.FIVE, CardSuit.SPADES),
            Card(CardValue.SIX, CardSuit.SPADES),
            Card(CardValue.SEVEN, CardSuit.SPADES),
            Card(CardValue.EIGHT, CardSuit.SPADES),
            Card(CardValue.NINE, CardSuit.SPADES),
            Card(CardValue.TEN, CardSuit.SPADES),
            Card(CardValue.JOCKEY, CardSuit.SPADES),
            Card(CardValue.QUEEN, CardSuit.SPADES),
            Card(CardValue.KING, CardSuit.SPADES),
            Card(CardValue.ACE, CardSuit.SPADES),
        )

        val actual = sut.buildCardDeck()

        assert(actual.containsAll(expected))
    }

//    @Test
//    fun `Should build correct Card deck`() {
//        val expected = diamodsSuit + spadesSuit + heartsSuit + clubsSuit
//
//        val actual = sut.buildCardDeck()
//
//        assertEquals(expected, actual)
//    }

        @Test
    fun `Should build correct cardDeck for Diamons suit`() {
        val expected = listOf(
            Card(CardValue.TWO, CardSuit.DIAMONDS),
            Card(CardValue.THREE, CardSuit.DIAMONDS),
            Card(CardValue.FOUR, CardSuit.DIAMONDS),
            Card(CardValue.FIVE, CardSuit.DIAMONDS),
            Card(CardValue.SIX, CardSuit.DIAMONDS),
            Card(CardValue.SEVEN, CardSuit.DIAMONDS),
            Card(CardValue.EIGHT, CardSuit.DIAMONDS),
            Card(CardValue.NINE, CardSuit.DIAMONDS),
            Card(CardValue.TEN, CardSuit.DIAMONDS),
            Card(CardValue.JOCKEY, CardSuit.DIAMONDS),
            Card(CardValue.QUEEN, CardSuit.DIAMONDS),
            Card(CardValue.KING, CardSuit.DIAMONDS),
            Card(CardValue.ACE, CardSuit.DIAMONDS)
        )

        val actual = sut.buildCardDeck()

        assert(actual.containsAll(expected))
    }

    @Test
    fun `Should build correct cardDeck for Hearts suit`() {
        val expected = listOf(
            Card(CardValue.TWO, CardSuit.HEARTS),
            Card(CardValue.THREE, CardSuit.HEARTS),
            Card(CardValue.FOUR, CardSuit.HEARTS),
            Card(CardValue.FIVE, CardSuit.HEARTS),
            Card(CardValue.SIX, CardSuit.HEARTS),
            Card(CardValue.SEVEN, CardSuit.HEARTS),
            Card(CardValue.EIGHT, CardSuit.HEARTS),
            Card(CardValue.NINE, CardSuit.HEARTS),
            Card(CardValue.TEN, CardSuit.HEARTS),
            Card(CardValue.JOCKEY, CardSuit.HEARTS),
            Card(CardValue.QUEEN, CardSuit.HEARTS),
            Card(CardValue.KING, CardSuit.HEARTS),
            Card(CardValue.ACE, CardSuit.HEARTS)
        )

        val actual = sut.buildCardDeck()

        assert(actual.containsAll(expected))
    }

    @Test
    fun `Should build correct cardDeck for Clubs suit`() {
        val expected = listOf(
            Card(CardValue.TWO, CardSuit.CLUBS),
            Card(CardValue.THREE, CardSuit.CLUBS),
            Card(CardValue.FOUR, CardSuit.CLUBS),
            Card(CardValue.FIVE, CardSuit.CLUBS),
            Card(CardValue.SIX, CardSuit.CLUBS),
            Card(CardValue.SEVEN, CardSuit.CLUBS),
            Card(CardValue.EIGHT, CardSuit.CLUBS),
            Card(CardValue.NINE, CardSuit.CLUBS),
            Card(CardValue.TEN, CardSuit.CLUBS),
            Card(CardValue.JOCKEY, CardSuit.CLUBS),
            Card(CardValue.QUEEN, CardSuit.CLUBS),
            Card(CardValue.KING, CardSuit.CLUBS),
            Card(CardValue.ACE, CardSuit.CLUBS)
        )

        val actual = sut.buildCardDeck()

        assert(actual.containsAll(expected))
    }
}