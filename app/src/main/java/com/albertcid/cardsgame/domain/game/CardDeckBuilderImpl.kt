package com.albertcid.cardsgame.domain.game

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardSuit
import com.albertcid.cardsgame.domain.model.CardValue

class CardDeckBuilderImpl : CardDeckBuilder {
    override fun build(): Set<Card> {
        return getSuitCards(CardSuit.SPADES) +
                getSuitCards(CardSuit.DIAMONDS) +
                getSuitCards(CardSuit.HEARTS) +
                getSuitCards(CardSuit.CLUBS)
    }

    private fun getSuitCards(suit: CardSuit) = setOf(
        Card(CardValue.TWO, suit),
        Card(CardValue.THREE, suit),
        Card(CardValue.FOUR, suit),
        Card(CardValue.FIVE, suit),
        Card(CardValue.SIX, suit),
        Card(CardValue.SEVEN, suit),
        Card(CardValue.EIGHT, suit),
        Card(CardValue.NINE, suit),
        Card(CardValue.TEN, suit),
        Card(CardValue.JOCKEY, suit),
        Card(CardValue.QUEEN, suit),
        Card(CardValue.KING, suit),
        Card(CardValue.ACE, suit)
    )
}