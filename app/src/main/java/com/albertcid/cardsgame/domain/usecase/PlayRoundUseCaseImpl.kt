package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.game.Player

class PlayRoundUseCaseImpl(
    private val gameTable: GameTable,
    private val userPlayer: Player
) : PlayRoundUseCase{
    override fun invoke(): GameStatus {
        gameTable.playRound()
    }

}