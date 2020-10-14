package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameTableImpl @Inject constructor(
    private val userPlayer: Player,
    private val opponentPlayer: Player,
    private val cardShuffler: CardShuffler
) : GameTable {
    override var round = 0
    private var suitPriority = listOf<CardSuit>()
    private lateinit var userPlayerCard: Card
    private lateinit var opponentPlayerCard: Card

    private var isUserWinnerOfRound = false

    private fun getGameStatus(): GameStatus {
        return GameStatus(
            currentRound = round,
            isUserWinnerOfRound = isUserWinnerOfRound,
            userCardPlayed = if (round > 0) userPlayerCard else null,
            opponentCardPlayed = if (round > 0) opponentPlayerCard else null,
            totalUsersCardPile = userPlayer.cardPile.size,
            totalUsersDiscardPile = userPlayer.discardPile.size,
            totalOpponentDiscardPile = opponentPlayer.discardPile.size
        )

    }

    override fun startGame(): GameStatus {
        round = 0
        suitPriority = cardShuffler.generateSuitPriority()
        clearPiles(userPlayer)
        clearPiles(opponentPlayer)
        assignCardsToPlayers()
        return getGameStatus()
    }


    private fun clearPiles(player: Player) {
        with(player) {
            cardPile.clear()
            discardPile.clear()
        }
    }


    private fun assignCardsToPlayers() {
        userPlayer.cardPile.addAll(cardShuffler.assignCards())
        opponentPlayer.cardPile.addAll(cardShuffler.assignCards())
    }



    override fun playRound(): GameStatus {
        round += 1
        userPlayerCard = userPlayer.playCard()
        opponentPlayerCard = opponentPlayer.playCard()

        if (userPlayerCard.value == opponentPlayerCard.value) {
            if (suitPriority.indexOf(userPlayerCard.suit) < suitPriority.indexOf(opponentPlayerCard.suit)) {
                userPlayer.winRound(userPlayerCard, opponentPlayerCard)
                isUserWinnerOfRound = true
            } else {
                opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
                isUserWinnerOfRound = false
            }

        } else if (userPlayerCard.value > opponentPlayerCard.value) {
            userPlayer.winRound(userPlayerCard, opponentPlayerCard)
            isUserWinnerOfRound = true
        } else {
            opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
            isUserWinnerOfRound = false
        }
        return getGameStatus()
    }
}