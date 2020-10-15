package com.albertcid.cardsgame.domain.game


import com.albertcid.cardsgame.clubsSuit
import com.albertcid.cardsgame.diamondsSuit
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.heartsSuit
import com.albertcid.cardsgame.spadesSuit
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class CardShufflerTest {

    private lateinit var sut: CardShuffler

    private val cardDeckBuilder = mock<CardDeckBuilder>()

    private val captorCards = argumentCaptor<MutableList<Card>>()

    private val playerOne = mock<Player>()
    private val playerTwo = mock<Player>()


    @Test
    fun `Should assign same numbers of cards to each player`() {
        givenFullCardDeckToAssign()
        val totalCardSizeEachPlayer = 26


        sut.assignCardsToPlayers(playerOne, playerTwo)

        verify(playerOne).receiveCardPile(captorCards.capture())
        verify(playerTwo).receiveCardPile(captorCards.capture())
        assertEquals(totalCardSizeEachPlayer, captorCards.firstValue.size)
        assertEquals(totalCardSizeEachPlayer, captorCards.secondValue.size)

    }


    @Test
    fun `Should assign different of cards to each player`() {
        givenFullCardDeckToAssign()
        val playerOne = mock<Player>()
        val playerTwo = mock<Player>()

        sut.assignCardsToPlayers(playerOne, playerTwo)

        verify(playerOne).receiveCardPile(captorCards.capture())
        verify(playerTwo).receiveCardPile(captorCards.capture())
        assertNotEquals(captorCards.firstValue.toList(), captorCards.secondValue.toList())
    }

    private fun givenFullCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit + heartsSuit + clubsSuit)
        sut = CardShufflerImpl(cardDeckBuilder)
    }

    private fun givenHalfCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit)
        sut = CardShufflerImpl(cardDeckBuilder)
    }
}