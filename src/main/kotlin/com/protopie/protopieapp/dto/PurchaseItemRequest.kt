package com.protopie.protopieapp.dto

import com.protopie.protopieapp.enums.ItemType
import com.protopie.protopieapp.enums.PaymentType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "자판기 상품 구매 요청", name = "PurchaseItemRequest")
data class PurchaseItemRequest(
    @Schema(description = "상품 타입")
    val itemType: ItemType,
    @Schema(description = "지불 수단 타입")
    val paymentType: PaymentType,
    @Schema(description = "(현금인 경우) 권종과 수량")
    val cashList: List<CashDto>?,
)
