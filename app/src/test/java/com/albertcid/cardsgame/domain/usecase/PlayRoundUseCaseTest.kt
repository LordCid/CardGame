package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.domain.game.GameTable
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class PlayRoundUseCaseTest {

    private lateinit var sut : PlayRoundUseCase
    private val gameTable = mock<GameTable>()

    @Before
    fun setUp() {
        sut = PlayRoundUseCaseImpl(gameTable)
    }

    @Test
    fun `When play round new game status is returned`() {
        gameTable.playRound()
    }
}