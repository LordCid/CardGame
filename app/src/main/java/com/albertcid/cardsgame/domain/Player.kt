package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card

interface Player {
    val discardPile: MutableSet<Card>
    fun recibeCardsToPlay(playablePile: MutableSet<Card>)
    fun playCard(): Card
    fun winRound(vararg cards: Card)
}