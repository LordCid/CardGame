package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card

interface Player {
    val discardPile: MutableSet<Card>
    val cardPile: MutableList<Card>
//    fun recibeCardsToPlay(playablePile: MutableSet<Card>)
    fun playCard(): Card
    fun winRound(vararg cards: Card)
}