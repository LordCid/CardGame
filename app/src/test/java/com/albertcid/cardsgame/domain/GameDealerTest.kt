package com.albertcid.cardsgame.domain


import com.albertcid.cardsgame.domain.model.Card
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class GameDealerTest {

    private lateinit var sut: GameDealer

    private val cardDeckBuilder = mock<CardDeckBuilder>()

    private val playerOne = mock<Player>()
    private val playerTwo = mock<Player>()

    private val captorCards = argumentCaptor<MutableSet<Card>>()


    @Test
    fun `Given full cards deck, must assign 26 cards to a player`() {
        givenFullCardDeckToAssign()
        val totalCardSizeEachPlayer = 26

        val actual = sut.assignCards()

        assertEquals(totalCardSizeEachPlayer, actual.size)
    }

    @Test
    fun `Given half cards deck, must assign 26 cards to a player`() {
        givenHalfCardDeckToAssign()
        val totalCardSizeEachPlayer = 26

        val actual = sut.assignCards()

        assertEquals(totalCardSizeEachPlayer, actual.size)
    }

    @Test
    fun `Given full cards deck, should remove the cards from them when assing to Player`() {
        givenFullCardDeckToAssign()
        val expected = getFullCardDeck().toMutableSet()

        val actual = sut.assignCards()
        expected.removeAll( actual)

        assertFalse(expected.contains(actual))
    }


    @Test
    fun `Given half cards deck, should remove the cards from them when assing to Player`() {
        givenHalfCardDeckToAssign()
        val expected = getFullCardDeck().toMutableSet()

        val actual = sut.assignCards()
        expected.removeAll( actual)

        assertFalse(expected.contains(actual))
    }


    @Ignore
    @Test
    fun `Should assign same numbers of cards to each player`() {
        val totalCardSizeEachPlayer = 26

        sut.assignCardsToPlayer()

        verify(playerOne).recibeCardsToPlay(captorCards.capture())
        verify(playerTwo).recibeCardsToPlay(captorCards.capture())
        assertEquals(totalCardSizeEachPlayer, captorCards.firstValue.size)
        assertEquals(totalCardSizeEachPlayer, captorCards.secondValue.size)

    }

    @Ignore
    @Test
    fun `Should assign different of cards to each player`() {
        sut.assignCardsToPlayer()

        verify(playerOne).recibeCardsToPlay(captorCards.capture())
        verify(playerTwo).recibeCardsToPlay(captorCards.capture())
        assertNotEquals(captorCards.firstValue.toList(), captorCards.secondValue.toList())
    }

    private fun givenFullCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit + heartsSuit + clubsSuit)
        sut = GameDealerImpl(cardDeckBuilder, playerOne, playerTwo)
    }

    private fun givenHalfCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit)
        sut = GameDealerImpl(cardDeckBuilder, playerOne, playerTwo)
    }

}