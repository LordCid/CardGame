package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import javax.inject.Inject

class RestartGameUseCaseImpl @Inject constructor(
    private val gameTable: GameTable
) : RestartGameUseCase {
    override fun invoke(): GameStatus {
        return gameTable.startGame()
    }
}