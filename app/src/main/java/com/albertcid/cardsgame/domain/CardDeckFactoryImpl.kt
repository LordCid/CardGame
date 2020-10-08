package com.albertcid.cardsgame.domain

class CardDeckFactoryImpl : CardDeckFactory {
    override fun buildCardDeck(): List<Card> {
        return getSuitCards(CardSuit.SPADES) +
                getSuitCards(CardSuit.DIAMONDS) +
                getSuitCards(CardSuit.HEARTS) +
                getSuitCards(CardSuit.CLUBS)
    }

    private fun getSuitCards(suit: CardSuit) = listOf(
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