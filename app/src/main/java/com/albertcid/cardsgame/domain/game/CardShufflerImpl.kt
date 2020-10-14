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
}