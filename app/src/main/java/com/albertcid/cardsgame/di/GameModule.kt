package com.albertcid.cardsgame.di

import com.albertcid.cardsgame.domain.game.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface GameModule {
    @Binds
    @Singleton
    fun bindPlayRoundUseCase(gameTable: GameTableImpl): GameTable

    @Binds
    fun bindPlayerInstance(player: PlayerImpl): Player

    @Binds
    fun bindCardShuffler(cardShuffler: CardShufflerImpl): CardShuffler

    @Binds
    fun bindCardDeckBuilder(cardDeckBuilder: CardDeckBuilderImpl): CardDeckBuilder

}