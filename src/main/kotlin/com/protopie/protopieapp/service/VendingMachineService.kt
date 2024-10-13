package com.protopie.protopieapp.service

import com.protopie.protopieapp.domain.cash.CashRepository
import com.protopie.protopieapp.domain.menu.Menu
import com.protopie.protopieapp.domain.menu.MenuRepository
import com.protopie.protopieapp.dto.CashDto
import com.protopie.protopieapp.dto.PurchaseItemResponse
import com.protopie.protopieapp.enums.CashType
import com.protopie.protopieapp.enums.ItemType
import com.protopie.protopieapp.enums.PaymentType
import com.protopie.protopieapp.helper.CommonException
import com.protopie.protopieapp.helper.CustomException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class VendingMachineService(
    private val memuRepository: MenuRepository,
    private val cashRepository: CashRepository,
) {
    @Transactional
    fun buyItem(
        itemType: ItemType,
        paymentType: PaymentType,
        cashList: List<CashDto>?
    ): PurchaseItemResponse? {
        val item = memuRepository.findByItem(itemType = itemType) ?: throw CustomException(
            CommonException.ITEM_NOT_FOUND,
            itemType.code
        )

        validateItemStock(item = item)

        when (paymentType) {
            PaymentType.CASH -> {
                item.buy()
                return calculateChange(item = item, cashList = cashList)
            }

            PaymentType.CARD -> {
                item.buy()
                return null
            }
        }
    }

    private fun validateItemStock(item: Menu) {
        if (item.qty < 1) {
            throw CustomException(CommonException.ITEM_QTY_MUST_BE_GRATER_THAN_ONE)
        }
    }

    private fun calculateChange(item: Menu, cashList: List<CashDto>?): PurchaseItemResponse {
        var fiveThousandNote = 0L
        var oneThousandNote = 0L
        var fiveHundredCoin = 0L
        var oneHundredCoin = 0L

        val cashInput = cashList?.sumOf { it.type.amount * it.qty }
            ?: 0

        if (cashInput <= 0) {
            throw CustomException(CommonException.CASH_MUST_BE_GRATER_THAN_ZERO)
        }

        if (cashInput < item.price) {
            throw CustomException(CommonException.CASH_INPUT_MUST_BE_GRATER_THAN_ITEM_PRICE)
        }

        var remainder = cashInput - item.price

        if (remainder >= CashType.FIVE_THOUSAND.amount) {
            fiveThousandNote = remainder.div(CashType.FIVE_THOUSAND.amount)
            remainder -= fiveThousandNote * CashType.FIVE_THOUSAND.amount
            deductChange(cashType = CashType.FIVE_THOUSAND, chageQty = fiveThousandNote)
        }

        if (remainder >= CashType.THOUSAND.amount) {
            oneThousandNote = remainder.div(CashType.THOUSAND.amount)
            remainder -= oneThousandNote * CashType.THOUSAND.amount
            deductChange(cashType = CashType.THOUSAND, chageQty = oneThousandNote)
        }

        if (remainder >= CashType.FIVE_HUNDRED.amount) {
            fiveHundredCoin = remainder.div(CashType.FIVE_HUNDRED.amount)
            remainder -= fiveHundredCoin * CashType.FIVE_HUNDRED.amount
            deductChange(cashType = CashType.FIVE_HUNDRED, chageQty = fiveHundredCoin)
        }

        if (remainder >= CashType.HUNDRED.amount) {
            oneHundredCoin = remainder.div(CashType.HUNDRED.amount)
            remainder -= oneHundredCoin * CashType.HUNDRED.amount
            deductChange(cashType = CashType.HUNDRED, chageQty = oneHundredCoin)
        }

        return PurchaseItemResponse(
            fiveThousandNote = fiveThousandNote,
            oneThousandNote = oneThousandNote,
            fiveHundredCoin = fiveHundredCoin,
            oneHundredCoin = oneHundredCoin,
        )
    }

    private fun deductChange(cashType: CashType, chageQty: Long) {
        val change = cashRepository.findByType(type = cashType)
            ?: throw CustomException(CommonException.CASH_NOT_FOUND)

        if (change.qty < 1) {
            throw CustomException(CommonException.CHANGE_QTY_MUST_BE_GRATER_THAN_ZERO)
        } else if (change.qty < chageQty) {
            throw CustomException(CommonException.CASH_QTY_MUST_BE_GRATER_THAN_REQUESTED_QTY)
        }
        change.deduct(changeQty = chageQty)
    }
}
