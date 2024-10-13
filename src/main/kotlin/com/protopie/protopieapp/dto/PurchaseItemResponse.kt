package com.protopie.protopieapp.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "자판기 상품 구매 응답", name = "PurchaseItemRequest")
data class PurchaseItemResponse(
    val fiveThousandNote: Long,
    val oneThousandNote: Long,
    val fiveHundredCoin: Long,
    val oneHundredCoin: Long,
)
