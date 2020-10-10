package com.albertcid.cardsgame.domain

class GameTableImpl(
    val playerOne: Player,
    val playerTwo: Player,
    private val gameCardShuffler: GameCardShuffler
) : GameTable {
    override var round = 0


    override fun playRound() {
        val cardOne = playerOne.playCard()
        val cardTwo = playerTwo.playCard()
        val suitPriority = gameCardShuffler.suitPriority

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