package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.clubsSuit
import com.albertcid.cardsgame.diamondsSuit
import com.albertcid.cardsgame.domain.game.CardDeckBuilder
import com.albertcid.cardsgame.domain.game.CardDeckBuilderImpl
import com.albertcid.cardsgame.heartsSuit
import com.albertcid.cardsgame.spadesSuit
import org.junit.Before

import org.junit.Test

class CardDeckBuilderTest {

    private lateinit var sut: CardDeckBuilder



    @Before
    fun setUp() {
        sut = CardDeckBuilderImpl()
    }

    @Test
    fun `Should build correct cardDeck for Spades suit`() {
        val actual = sut.build()

        assert(actual.containsAll(spadesSuit))
    }



        @Test
    fun `Should build correct cardDeck for Diamons suit`() {
        val actual = sut.build()

        assert(actual.containsAll(diamondsSuit))
    }

    @Test
    fun `Should build correct cardDeck for Hearts suit`() {
        val actual = sut.build()

        assert(actual.containsAll(heartsSuit))
    }

    @Test
    fun `Should build correct cardDeck for Clubs suit`() {
        val actual = sut.build()

        assert(actual.containsAll(clubsSuit))
    }

    //    @Test
//    fun `Should build correct Card deck`() {
//        val expected = diamodsSuit + spadesSuit + heartsSuit + clubsSuit
//
//        val actual = sut.buildCardDeck()
//
//        assertEquals(expected, actual)
//    }
}