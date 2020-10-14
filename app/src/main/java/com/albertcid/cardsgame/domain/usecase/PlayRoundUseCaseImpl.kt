package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import javax.inject.Inject

class PlayRoundUseCaseImpl @Inject constructor(
    private val gameTable: GameTable
) : PlayRoundUseCase {
    override fun invoke(): GameStatus {
        return gameTable.playRound()
    }
}