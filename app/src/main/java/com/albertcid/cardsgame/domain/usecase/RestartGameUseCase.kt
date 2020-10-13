package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.GameStatus

interface RestartGameUseCase {
    operator fun invoke(): GameStatus
}