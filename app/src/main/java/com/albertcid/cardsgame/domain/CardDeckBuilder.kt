package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card

interface CardDeckBuilder {
    fun build(): Set<Card>
}