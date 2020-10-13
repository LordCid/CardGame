package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus

interface PlayRoundUseCase {
    operator fun invoke(): GameStatus
}