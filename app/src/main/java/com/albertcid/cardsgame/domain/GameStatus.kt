package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

data class GameStatus(
        val currentRound: Int,
        val isUserWinnerOfRound: Boolean,
        val isGameFinished: Boolean,
        val userCardPlayed: Card,
        val opponentCardPlayed: Card,
        val totalUsersCardPile: Int,
        val totalUsersDiscardPile: Int,
        val totalOpponentDiscardPile: Int,
        val suitPriority: List<CardSuit>
)