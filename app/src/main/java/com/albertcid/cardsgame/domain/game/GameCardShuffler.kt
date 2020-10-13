package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

interface GameCardShuffler {
    fun generateSuitPriority(): List<CardSuit>
    fun assignCards(): MutableSet<Card>
}