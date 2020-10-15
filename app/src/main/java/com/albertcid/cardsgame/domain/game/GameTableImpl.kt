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
    private var round = 0
    private var suitPriority = listOf<CardSuit>()
    private var userPlayerCard = Card()
    private var opponentPlayerCard  = Card()


    override fun startGame(): GameStatus {
        round = 0
        userPlayerCard = Card()
        opponentPlayerCard = Card()
        suitPriority = cardShuffler.generateSuitPriority()
        clearDiscardPiles()
        assignCardsToPlayers()
        return getGameStatus(isUserWinnerOfRound = false, gameFinished = false)
    }

    private fun clearDiscardPiles() {
        userPlayer.clearDecks()
        opponentPlayer.clearDecks()
    }

    private fun assignCardsToPlayers() {
        cardShuffler.assignCardsToPlayers(userPlayer, opponentPlayer)
    }



    override fun playRound(): GameStatus {
        round += 1
        userPlayerCard = userPlayer.playCard()
        opponentPlayerCard = opponentPlayer.playCard()
        val isUserWinnerOfRound = when {
            userPlayerCard.value == opponentPlayerCard.value -> {
                compareHigherSuit()
            }
            userPlayerCard.value > opponentPlayerCard.value -> {
                userPlayer.winRound(userPlayerCard, opponentPlayerCard)
                true
            }
            else -> {
                opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
                false
            }
        }

        return getGameStatus(isUserWinnerOfRound, gameFinished = round == 26)
    }

    private fun compareHigherSuit(): Boolean {
       return if (suitPriority.indexOf(userPlayerCard.suit) < suitPriority.indexOf(opponentPlayerCard.suit)
            ) {
            userPlayer.winRound(userPlayerCard, opponentPlayerCard)
            true
        } else {
            opponentPlayer.winRound(userPlayerCard, opponentPlayerCard)
            false
        }
    }

    private fun getGameStatus(isUserWinnerOfRound: Boolean, gameFinished: Boolean): GameStatus {
        return GameStatus(
            currentRound = round,
            isUserWinnerOfRound = isUserWinnerOfRound,
            isGameFinished = gameFinished,
            userCardPlayed = userPlayerCard,
            opponentCardPlayed = opponentPlayerCard,
            totalUsersCardPile = userPlayer.getCardPileSize(),
            totalUsersDiscardPile = userPlayer.getDiscardPileSize(),
            totalOpponentDiscardPile = opponentPlayer.getDiscardPileSize(),
            suitPriority = suitPriority
        )

    }

}