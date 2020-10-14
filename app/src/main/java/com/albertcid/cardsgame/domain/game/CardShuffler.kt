package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

interface CardShuffler {
    fun generateSuitPriority(): List<CardSuit>
    fun assignCards(): MutableSet<Card>
    fun assignCardsToPlayers(playerOne: Player, playerTwo: Player)
}