package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable

class PlayRoundUseCaseImpl(
    private val gameTable: GameTable
) : PlayRoundUseCase {
    override fun invoke(): GameStatus {
        gameTable.playRound()
        return gameTable.getGameStatus()
    }
}