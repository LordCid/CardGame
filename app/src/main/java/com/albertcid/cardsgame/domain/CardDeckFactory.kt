package com.albertcid.cardsgame.domain

interface CardDeckFactory {
    fun buildCardDeck(): List<Card>
}