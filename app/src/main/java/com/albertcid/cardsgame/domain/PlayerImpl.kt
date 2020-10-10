package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card

class PlayerImpl : Player {
    override val discardPile: MutableSet<Card>
        get() = TODO("Not yet implemented")
    private lateinit var playablePile: MutableSet<Card>

    override fun recibeCardsToPlay(playablePile: MutableSet<Card>) {
        TODO("Not yet implemented")
    }

    override fun playCard(): Card {
        TODO("Not yet implemented")
    }

    override fun winRound(vararg cards: Card) {
        TODO("Not yet implemented")
    }
}