package com.albertcid.cardsgame.domain.model

data class Card (
    val value: CardValue = CardValue.NONE,
    val suit: CardSuit = CardSuit.CLUBS
)
