package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable

class RestartGameUseCaseImpl(
    private val gameTable: GameTable
) : RestartGameUseCase {
    override fun invoke(): GameStatus {
        gameTable.startGame()
        return gameTable.getGameStatus()
    }
}