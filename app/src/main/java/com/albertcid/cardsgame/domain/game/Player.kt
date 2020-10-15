package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card

interface Player {
    fun playCard(): Card
    fun winRound(vararg cards: Card)
    fun receiveCardPile(cards: MutableList<Card>)
    fun getCardPileSize(): Int
    fun getDiscardPileSize(): Int
    fun clearDecks()
}