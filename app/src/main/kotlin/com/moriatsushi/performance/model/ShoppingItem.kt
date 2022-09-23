package com.moriatsushi.performance.model

import java.util.Date
import java.util.UUID
import kotlin.math.max

data class ShoppingItem(
    val id: String,
    val name: String,
    val count: Int,
    val added: Date
) {
    companion object {
        fun create(
            name: String,
            count: Int
        ): ShoppingItem {
            return ShoppingItem(
                id = UUID.randomUUID().toString(),
                name = name,
                count = count,
                added = Date()
            )
        }
    }

    fun increase(): ShoppingItem {
        return copy(count = count + 1)
    }

    fun decrease(): ShoppingItem {
        return copy(count = max(count - 1, 0))
    }
}
