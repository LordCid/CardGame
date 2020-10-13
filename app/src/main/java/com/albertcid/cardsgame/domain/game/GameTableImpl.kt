package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit

class GameTableImpl(
    override val userPlayer: Player,
    override val opponentPlayer: Player,
    private val gameCardShuffler: GameCardShuffler
) : GameTable {
    override var round = 0
    private var suitPriority = listOf<CardSuit>()
    private lateinit var cardOne : Card
    private lateinit var cardTwo : Card

    private var isUserWinnerOfRound = false

    override fun getGameStatus(): GameStatus {
        return GameStatus(
            currentRound = round,
            isUserWinnerOfRound = isUserWinnerOfRound,
            userCardPlayed = cardOne,
            opponentCardPlayed = cardTwo,
            totalUsersCardPile = userPlayer.cardPile.size,
            totalUsersDiscardPile = userPlayer.discardPile.size,
            totalOpponentDiscardPile = opponentPlayer.discardPile.size
        )

    }


    override fun startGame() {
        round = 0
        suitPriority = gameCardShuffler.generateSuitPriority()
        assignCardsToPlayers()
        clearDiscardPiles()
    }

    private fun assignCardsToPlayers() {
        userPlayer.cardPile.addAll(gameCardShuffler.assignCards())
        opponentPlayer.cardPile.addAll(gameCardShuffler.assignCards())
    }

    private fun clearDiscardPiles() {
        userPlayer.discardPile.clear()
        opponentPlayer.discardPile.clear()
    }

    override fun playRound() {
        cardOne = userPlayer.playCard()
        cardTwo = opponentPlayer.playCard()
        round += 1

        if (cardOne.value == cardTwo.value) {
            if (suitPriority.indexOf(cardOne.suit) < suitPriority.indexOf(cardTwo.suit)) {
                userPlayer.winRound(cardOne, cardTwo)
                isUserWinnerOfRound = true
            } else {
                opponentPlayer.winRound(cardOne, cardTwo)
                isUserWinnerOfRound = false
            }

        } else if (cardOne.value > cardTwo.value) {
            userPlayer.winRound(cardOne, cardTwo)
            isUserWinnerOfRound = true
        } else {
            opponentPlayer.winRound(cardOne, cardTwo)
            isUserWinnerOfRound = false
        }
    }
}