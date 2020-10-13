package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue

class PlayRoundUseCaseImpl(
    private val gameTable: GameTable
) : PlayRoundUseCase {
    override fun invoke()= gameTable.getGameStatus()
}