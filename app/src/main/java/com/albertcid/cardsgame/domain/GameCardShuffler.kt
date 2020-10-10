package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

interface GameCardShuffler {
    val suitPriority: List<CardSuit>
    fun assignCards(): MutableSet<Card>
    fun assignCardsToPlayer()
}