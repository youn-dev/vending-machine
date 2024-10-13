package com.protopie.protopieapp.domain.cash

import com.protopie.protopieapp.enums.CashType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CashRepository : JpaRepository<Cash, Long> {
    fun findByType(type: CashType): Cash?
}
