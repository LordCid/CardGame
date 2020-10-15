package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus

interface GameTable {
    fun startGame(): GameStatus
    fun playRound(): GameStatus
}