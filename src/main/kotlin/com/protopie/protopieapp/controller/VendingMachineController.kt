package com.protopie.protopieapp.controller

import com.protopie.protopieapp.dto.PurchaseItemRequest
import com.protopie.protopieapp.dto.PurchaseItemResponse
import com.protopie.protopieapp.service.VendingMachineService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "자판기 상품")
@RequestMapping("v1/vending-machine/items")
@RestController
class VendingMachineController(
    private val vendingMachineService: VendingMachineService,
) {
    @Operation(summary = "자판기 상품 구매")
    @PatchMapping
    fun purchaseItem(
        @RequestBody request: PurchaseItemRequest,
    ): PurchaseItemResponse? {
        return vendingMachineService.buyItem(
            itemType = request.itemType,
            paymentType = request.paymentType,
            cashList = request.cashList,
        )
    }
}
