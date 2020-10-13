package com.albertcid.cardsgame.domain

import com.albertcid.cardsgame.domain.model.Card

fun Card?.toLiteral(): String{
    return this?.let {  "${it.value} ${it.suit}" } ?:""
}