package com.protopie.protopieapp.enums

enum class PaymentType(
    val code: String,
) {
    CASH(code = "cash"),
    CARD(code = "card"),
    ;
}
