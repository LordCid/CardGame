package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.GameStatus

interface GameTable {
    var round : Int
    val userPlayer: Player
    val opponentPlayer: Player
    fun getGameStatus(): GameStatus
    fun startGame()
    fun playRound()
}