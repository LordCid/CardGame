package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card

interface CardDeckFactory {
    fun buildCardDeck(): List<Card>
}