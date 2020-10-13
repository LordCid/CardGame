package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable

class PlayRoundUseCaseImpl(
    private val gameTable: GameTable
) : PlayRoundUseCase{
    override fun invoke(): GameStatus {
        TODO("Not yet implemented")
    }

}