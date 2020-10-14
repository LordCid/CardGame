package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.diamondsSuit
import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import com.albertcid.cardsgame.randomFullUserCardDeck
import com.albertcid.cardsgame.randomOpponentFullCardDeck
import com.albertcid.cardsgame.spadesSuit
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
        val someCards = (spadesSuit + diamondsSuit).toMutableSet()
        given(cardShuffler.assignCards()).willReturn(someCards)
        sut.round = 3

        sut.startGame()

        assertTrue(sut.round == 0)
    }

    @Test
    fun `When start game each player should receive cards to play`() {
        val someCards = randomFullUserCardDeck
        given(cardShuffler.assignCards()).willReturn(someCards)
        givenStubedPileCards()

        sut.startGame()

        verify(userPlayer.cardPile).addAll(someCards)
        verify(opponentPlayer.cardPile).addAll(someCards)
    }

    @Test
    fun `When start game each player pilse cards is set empty`() {
        val someCards = randomFullUserCardDeck
        given(cardShuffler.assignCards()).willReturn(someCards)
        givenStubedPileCards()
        givenSubbedDiscardCards()

        sut.startGame()

        verify(userPlayer.cardPile).clear()
        verify(opponentPlayer.cardPile).clear()
        verify(userPlayer.discardPile).clear()
        verify(opponentPlayer.discardPile).clear()
    }

    @Test
    fun `When start game correct status is set`() {
        val someCards = randomFullUserCardDeck
        given(cardShuffler.assignCards()).willReturn(someCards)
        givenStubedPileCards()
        givenSubbedDiscardCards()
        val expected = GameStatus(
            currentRound = 0,
            isUserWinnerOfRound = false,
            userCardPlayed = null,
            opponentCardPlayed = null,
            totalUsersCardPile = 26,
            totalUsersDiscardPile = 0,
            totalOpponentDiscardPile = 0
        )

        val actual = sut.startGame()

       assertEquals(expected, actual)
    }


//    @Test
//    fun `When finish game correct status is set`() {
//        val someCards = randomFullUserCardDeck
//        given(cardShuffler.assignCards()).willReturn(someCards)
//        givenStubedPileCards()
//        givenSubbedDiscardCards()
//        val expected = GameStatus(
//            currentRound = 0,
//            isUserWinnerOfRound = false,
//            userCardPlayed = null,
//            opponentCardPlayed = null,
//            totalUsersCardPile = 26,
//            totalUsersDiscardPile = 0,
//            totalOpponentDiscardPile = 0
//        )
//
//        val actual = sut.startGame()
//
//        assertEquals(expected, actual)
//    }

//    @Test
//    fun `Given user player lose first round, should return correct Game Status is returned`() {
//        givenFirstRound()
//        val cardOne = Card(CardValue.TWO, CardSuit.HEARTS)
//        val cardTwo = Card(CardValue.KING, CardSuit.DIAMONDS)
//        given(userPlayer.playCard()).willReturn(cardOne)
//        given(opponentPlayer.playCard()).willReturn(cardTwo)
//
//        val expected = GameStatus(
//            currentRound = 1,
//            isUserWinnerOfRound = false,
//            userCardPlayed = cardOne,
//            opponentCardPlayed = cardTwo,
//            totalUsersCardPile = 25,
//            totalUsersDiscardPile = 0,
//            totalOpponentDiscardPile = 2
//        )
//
//        val actual = sut.playRound()
//
//        assertEquals(expected, actual)
//    }
//
//    private fun givenFirstRound() {
//        given(cardShuffler.assignCards()).willReturn(randomFullUserCardDeck)
//        sut.startGame()
//    }


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
        given(userPlayer.playCard()).willReturn(randomCard)
        given(opponentPlayer.playCard()).willReturn(randomCard)

        sut.playRound()

        assertEquals(expected, sut.round)
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
        val someCards = randomFullUserCardDeck
        given(cardShuffler.assignCards()).willReturn(someCards)
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

        sut.playRound()

        verify(userPlayer, times(1)).winRound(cardOne, cardTwo)
        verify(opponentPlayer, never()).winRound(any())
    }

//    @Test
//    fun `Given finish game conditions are matched and user player is winner, correct status is returned`() {
//        TODO("Not yet implemented")
//    }



    private fun givenPlayedRound(
        cardOne: Card,
        cardTwo: Card
    ) {
        given(userPlayer.playCard()).willReturn(cardOne)
        given(opponentPlayer.playCard()).willReturn(cardTwo)
        sut.startGame()
    }

    private fun givenSubbedDiscardCards() {
        given(userPlayer.discardPile).willReturn(spy(mutableSetOf()))
        given(opponentPlayer.discardPile).willReturn(spy(mutableSetOf()))
    }

    private fun givenStubedPileCards() {
        given(userPlayer.cardPile).willReturn(spy(mutableListOf()))
        given(opponentPlayer.cardPile).willReturn(spy(mutableListOf()))
    }


}