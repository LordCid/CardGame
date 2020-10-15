package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.CardSuit

interface CardShuffler {
    fun generateSuitPriority(): List<CardSuit>
    fun assignCardsToPlayers(playerOne: Player, playerTwo: Player)
}