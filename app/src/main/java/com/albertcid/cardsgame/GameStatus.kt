package com.albertcid.cardsgame

data class GameStatus(
        val currentRound: Int,
        val isUserWinnerOfRound: Boolean,
        val userCardPlayed: String,
        val opponentCardPlayed: String,
        val totalUsersCardPile: Int,
        val totalUsersDiscardPile: Int,
        val totalOpponentDiscardPile: Int
)