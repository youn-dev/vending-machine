package com.protopie.protopieapp.dto

import com.protopie.protopieapp.enums.CashType

data class CashDto(
    val type: CashType,
    val qty: Long,
)
