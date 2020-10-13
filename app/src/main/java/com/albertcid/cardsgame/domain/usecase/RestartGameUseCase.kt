package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus

interface RestartGameUseCase {
    operator fun invoke(): GameStatus
}