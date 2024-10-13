package com.protopie.protopieapp.domain.cash

import com.protopie.protopieapp.enums.CashType
import com.protopie.protopieapp.helper.CommonException
import com.protopie.protopieapp.helper.CustomException
import jakarta.persistence.*

@Entity
@Table(name = "cash")
class Cash(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint not null")
    val id: Long = 0,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", columnDefinition = "varchar not null")
    val type: CashType,

    @Column(name = "qty", columnDefinition = "bigint not null")
    var qty: Long,
) {
    init {
        if (qty < 1) {
            throw CustomException(CommonException.CHANGE_QTY_MUST_BE_GRATER_THAN_ZERO)
        }
    }

    fun deduct(changeQty: Long) {
        qty -= changeQty
    }
}
