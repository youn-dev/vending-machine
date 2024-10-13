package com.protopie.protopieapp.helper

import com.protopie.protopieapp.domain.cash.Cash
import com.protopie.protopieapp.domain.cash.CashRepository
import com.protopie.protopieapp.domain.menu.Menu
import com.protopie.protopieapp.domain.menu.MenuRepository
import com.protopie.protopieapp.enums.CashType
import com.protopie.protopieapp.enums.ItemType
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val menuRepository: MenuRepository,
    private val cashRepository: CashRepository,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        menuRepository.save(
            Menu(
                item = ItemType.COKE,
                price = 1_100L,
                qty = 3,
            )
        )
        menuRepository.save(
            Menu(
                item = ItemType.WATER,
                price = 600L,
                qty = 3,
            )
        )
        menuRepository.save(
            Menu(
                item = ItemType.COFFEE,
                price = 700L,
                qty = 3,
            )
        )
        cashRepository.save(
            Cash(
                type = CashType.HUNDRED,
                qty = 50,
            )
        )
        cashRepository.save(
            Cash(
                type = CashType.FIVE_HUNDRED,
                qty = 10,
            )
        )
        cashRepository.save(
            Cash(
                type = CashType.THOUSAND,
                qty = 1,
            )
        )
        cashRepository.save(
            Cash(
                type = CashType.FIVE_THOUSAND,
                qty = 1,
            )
        )
    }
}
