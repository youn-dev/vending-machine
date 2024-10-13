package com.protopie.protopieapp.domain.menu

import com.protopie.protopieapp.enums.ItemType
import com.protopie.protopieapp.helper.CommonException
import com.protopie.protopieapp.helper.CustomException
import jakarta.persistence.*

@Entity
@Table(name = "menus")
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint not null")
    val id: Long = 0,

    @Column(name = "item", columnDefinition = "varchar not null")
    @Enumerated(EnumType.STRING)
    val item: ItemType,

    @Column(name = "price", columnDefinition = "bigint not null")
    val price: Long,

    @Column(name = "qty", columnDefinition = "bigint not null")
    var qty: Long,
) {
    init {
        if (qty < 2) {
            throw CustomException(CommonException.ITEM_QTY_MUST_BE_GRATER_THAN_ONE)
        }
    }

    fun buy() {
        qty -= 1
    }
}
