package com.albertcid.cardsgame

import com.albertcid.cardsgame.domain.model.Card

data class GameStatus(
        val currentRound: Int,
        val isUserWinnerOfRound: Boolean,
        val userCardPlayed: Card,
        val opponentCardPlayed: Card,
        val totalUsersCardPile: Int,
        val totalUsersDiscardPile: Int,
        val totalOpponentDiscardPile: Int
)