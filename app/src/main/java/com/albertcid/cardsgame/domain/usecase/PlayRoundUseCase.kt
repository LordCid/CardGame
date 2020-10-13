package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus

interface PlayRoundUseCase {
    operator fun invoke(): GameStatus
}