package com.albertcid.cardsgame.domain.usecase

import com.albertcid.cardsgame.GameStatus
import com.albertcid.cardsgame.domain.*
import com.albertcid.cardsgame.domain.game.GameTable
import com.albertcid.cardsgame.domain.game.Player
import com.albertcid.cardsgame.domain.game.PlayerImpl
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before

import org.junit.Test

class PlayRoundUseCaseTest {

    private lateinit var sut : PlayRoundUseCase
    private val gameTable = mock<GameTable>()

    @Before
    fun setUp() {
        sut = PlayRoundUseCaseImpl(gameTable)
    }

    @Test
    fun `Given use player lose round, When invoked, correct Game Status is returned`() {
        given(gameTable.userPlayer).willReturn( userPlayerLose() )
        given(gameTable.opponentPlayer).willReturn( oppentPlayerWins() )
//        val expected = GameStatus(
//
//        )

        val actual = sut.invoke()

        verify(gameTable).playRound()
    }

    private fun userPlayerLose(): Player{
        val player = PlayerImpl()
        player.cardPile.addAll(spadesSuit + diamondsSuit)
        player.discardPile.addAll(mutableSetOf())
        return player
    }

    private fun oppentPlayerWins(): Player{
        val player = PlayerImpl()
        player.cardPile.addAll(heartsSuit + clubsSuit)
        player.discardPile.addAll(mutableSetOf())
        return player
    }

}