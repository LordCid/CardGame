package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card
import com.albertcid.cardsgame.domain.model.CardValue

fun Card.toLiteral(): String{
//    return this?.let {  "${it.value} ${it.suit}" } ?:""
    return if (this.value != CardValue.NONE) "${this.value} ${this.suit}"  else  ""
}