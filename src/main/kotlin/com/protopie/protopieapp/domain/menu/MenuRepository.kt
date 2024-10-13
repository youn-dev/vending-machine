package com.protopie.protopieapp.domain.menu

import com.protopie.protopieapp.enums.ItemType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<Menu, Long> {
    fun findByItem(itemType: ItemType): Menu?
}
