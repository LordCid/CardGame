package com.albertcid.cardsgame.domain.game

interface GameTable {
    var round : Int
    fun startGame()
    fun playRound()
}