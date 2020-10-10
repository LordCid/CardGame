package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

class GameDealerImpl(
    cardDeckBuilder: CardDeckBuilder,
    private val playerOne: Player,
    private val playerTwo: Player
) : GameDealer {
    private var cardDeck : MutableSet<Card> = cardDeckBuilder.build() as MutableSet<Card>

    private val totalCardsForPlayer = 26
    override val suitPriority =
        listOf(CardSuit.SPADES, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.CLUBS).shuffled()

    override fun assignCards(): MutableSet<Card> {
        cardDeck.shuffled()
        return cardDeck.chunked(totalCardsForPlayer)[0].toMutableSet()
    }


    override fun assignCardsToPlayer() {
        cardDeck.shuffled()
        val chunkedCards =  cardDeck.chunked(cardDeck.size / 2)
        val playerOnesCard = chunkedCards[0].shuffled().toMutableSet()
        val playerTwoCard = chunkedCards[1].shuffled().toMutableSet()

        playerOne.recibeCardsToPlay(playerOnesCard)
        playerTwo.recibeCardsToPlay(playerTwoCard)
    }
}