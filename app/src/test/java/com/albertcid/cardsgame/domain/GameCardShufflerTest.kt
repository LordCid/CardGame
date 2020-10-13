package com.albertcid.cardsgame.domain


import com.albertcid.cardsgame.clubsSuit
import com.albertcid.cardsgame.diamondsSuit
import com.albertcid.cardsgame.domain.game.CardDeckBuilder
import com.albertcid.cardsgame.domain.game.GameCardShuffler
import com.albertcid.cardsgame.domain.game.GameCardShufflerImpl
import com.albertcid.cardsgame.heartsSuit
import com.albertcid.cardsgame.spadesSuit
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock

import org.junit.Assert.*
import org.junit.Test

class GameCardShufflerTest {

    private lateinit var sut: GameCardShuffler

    private val cardDeckBuilder = mock<CardDeckBuilder>()


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
        val expected = (spadesSuit + diamondsSuit + heartsSuit + clubsSuit).toMutableSet()

        val actual = sut.assignCards()
        expected.removeAll( actual)

        assertFalse(expected.contains(actual))
    }


    @Test
    fun `Given half cards deck, should remove the cards from them when assing to Player`() {
        givenHalfCardDeckToAssign()
        val expected = (spadesSuit + diamondsSuit + heartsSuit + clubsSuit).toMutableSet()

        val actual = sut.assignCards()
        expected.removeAll( actual)

        assertFalse(expected.contains(actual))
    }

    private fun givenFullCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit + heartsSuit + clubsSuit)
        sut = GameCardShufflerImpl(cardDeckBuilder)
    }

    private fun givenHalfCardDeckToAssign() {
        given(cardDeckBuilder.build()).willReturn(spadesSuit + diamondsSuit)
        sut = GameCardShufflerImpl(cardDeckBuilder)
    }

}