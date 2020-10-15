package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardValue

fun Card.toLiteral(): String{
    return if (this.value != CardValue.NONE) "${this.value} of ${this.suit}"  else  ""
}