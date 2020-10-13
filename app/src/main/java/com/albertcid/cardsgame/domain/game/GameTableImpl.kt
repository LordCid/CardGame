package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.CardSuit

class GameTableImpl(
    val playerOne: Player,
    val playerTwo: Player,
    private val gameCardShuffler: GameCardShuffler
) : GameTable {
    override var round = 0
    private var suitPriority = listOf<CardSuit>()

    override fun startGame() {
        round = 0
        suitPriority = gameCardShuffler.generateSuitPriority()
        playerOne.cardPile.addAll(gameCardShuffler.assignCards())
        playerTwo.cardPile.addAll(gameCardShuffler.assignCards())
    }

    override fun playRound() {
        val cardOne = playerOne.playCard()
        val cardTwo = playerTwo.playCard()

        if (cardOne.value == cardTwo.value) {
            if (suitPriority.indexOf(cardOne.suit) < suitPriority.indexOf(cardTwo.suit)) {
                playerOne.winRound(cardOne, cardTwo)
            } else {
                playerTwo.winRound(cardOne, cardTwo)
            }

        } else if (cardOne.value > cardTwo.value) {
            playerOne.winRound(cardOne, cardTwo)
        } else {
            playerTwo.winRound(cardOne, cardTwo)
        }


        round += 1
    }
}