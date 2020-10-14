package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus

interface GameTable {
    var round : Int
    fun startGame(): GameStatus
    fun playRound(): GameStatus
}