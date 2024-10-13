package com.protopie.protopieapp.enums

enum class ItemType(
    val code: String,
    val price: Long,
) {
    COKE(code = "coke", price = 1100L),
    WATER(code = "water", price = 600L),
    COFFEE(code = "coffee", price = 700L),
}
