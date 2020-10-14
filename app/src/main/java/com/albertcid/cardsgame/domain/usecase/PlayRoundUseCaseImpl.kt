package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
import javax.inject.Inject

class PlayRoundUseCaseImpl @Inject constructor(
    private val gameTable: GameTable
) : PlayRoundUseCase {
    override fun invoke(): GameStatus {
        gameTable.playRound()
        return gameTable.getGameStatus()
    }
}