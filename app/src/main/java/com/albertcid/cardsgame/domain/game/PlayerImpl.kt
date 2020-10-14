package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import javax.inject.Inject

class PlayerImpl
//@Inject constructor()
    : Player {
    override val discardPile = mutableSetOf<Card>()
    override val cardPile = mutableListOf<Card>()

    override fun playCard(): Card {
        val card = cardPile[0]
        cardPile.removeAt(0)
        return card
    }

    override fun winRound(vararg cards: Card) {
        discardPile.addAll(cards)
    }
}