package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.GameStatus
import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue
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
    private var userPlayerCard = Card()
    private var opponentPlayerCard = Card()

    private var isUserWinnerOfRound = false

    private fun getGameStatus(): GameStatus {
        return GameStatus(
            currentRound = round,
            isUserWinnerOfRound = isUserWinnerOfRound,
            isUserWinnerOfGame = false,
            userCardPlayed = userPlayerCard,
            opponentCardPlayed = opponentPlayerCard,
            totalUsersCardPile = userPlayer.getCardPileSize(),
            totalUsersDiscardPile = userPlayer.getDiscardPileSize(),
            totalOpponentDiscardPile = opponentPlayer.getDiscardPileSize()
        )

    }

    override fun startGame(): GameStatus {
        round = 0
        suitPriority = cardShuffler.generateSuitPriority()
        clearDiscardPiles()
        assignCardsToPlayers()
        return getGameStatus()
    }

    private fun clearDiscardPiles() {
        clearPiles(userPlayer)
        clearPiles(opponentPlayer)
    }

    private fun clearPiles(player: Player) {
        with(player) {
            cardPile.clear()
            discardPile.clear()
        }
    }


    private fun assignCardsToPlayers() {
        cardShuffler.assignCardsToPlayers(userPlayer, opponentPlayer)
    }



    override fun playRound(): GameStatus {
        round += 1
        userPlayerCard = userPlayer.playCard()
        opponentPlayerCard = opponentPlayer.playCard()

        when {
            userPlayerCard.value == opponentPlayerCard.value -> {
                compareHigherSuit()
            }
            userPlayerCard.value > opponentPlayerCard.value -> {
                userPlayer.winRound(userPlayerCard, opponentPlayerCard)
                isUserWinnerOfRound = true
            }
            else -> {
                opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
                isUserWinnerOfRound = false
            }
        }
        return getGameStatus()
    }

    private fun compareHigherSuit() {
        isUserWinnerOfRound =
            if (suitPriority.indexOf(userPlayerCard.suit) < suitPriority.indexOf(opponentPlayerCard.suit)
            ) {
            userPlayer.winRound(userPlayerCard, opponentPlayerCard)
            true
        } else {
            opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
            false
        }
    }
}