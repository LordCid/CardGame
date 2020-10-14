package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import javax.inject.Inject

class CardShufflerImpl @Inject constructor(
    private val cardDeckBuilder: CardDeckBuilder
) : CardShuffler {
    private var cardDeck : MutableSet<Card> = cardDeckBuilder.build() as MutableSet<Card>

    private val totalCardsForPlayer = 26

    override fun generateSuitPriority(): List<CardSuit> {
        return listOf(
            CardSuit.SPADES,
            CardSuit.DIAMONDS,
            CardSuit.HEARTS,
            CardSuit.CLUBS
        ).shuffled()
    }

    override fun assignCards(): MutableSet<Card> {
        cardDeck.shuffled()
        return cardDeck.chunked(totalCardsForPlayer)[0].toMutableSet()
    }

    override fun assignCardsToPlayers(playerOne: Player, playerTwo: Player) {
        cardDeck.shuffled()
        val chunkedCards =  cardDeck.chunked(cardDeck.size / 2)
        val playerOnesCard = chunkedCards[0].shuffled().toMutableList()
        val playerTwoCard = chunkedCards[1].shuffled().toMutableList()

        playerOne.receiveCardPile(playerOnesCard)
        playerTwo.receiveCardPile(playerTwoCard)
    }
}