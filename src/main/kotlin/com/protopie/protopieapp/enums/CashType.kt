package com.protopie.protopieapp.enums

enum class CashType(
    val code: String,
    val amount: Long,
) {
    HUNDRED(code = "hundred", amount = 100L),
    FIVE_HUNDRED(code = "five_hundred", amount = 500L),
    THOUSAND(code = "thousand", amount = 1_000L),
    FIVE_THOUSAND(code = "five_thousand", amount = 5_000L),
    ;
}
