package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

interface GameDealer {
    val suitPriority: List<CardSuit>
    fun assingCards(): MutableSet<Card>
    fun assignCardsToPlayer()
}