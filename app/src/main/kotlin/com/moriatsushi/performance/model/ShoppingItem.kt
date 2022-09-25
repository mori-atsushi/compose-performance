package com.moriatsushi.performance.model

import androidx.compose.runtime.Immutable
import java.util.Date
import java.util.UUID
import kotlin.math.max

@Immutable
data class ShoppingItem(
    val id: String,
    val name: String,
    val count: Int,
    val added: Date,
    val checked: Boolean
) {
    companion object {
        fun create(
            name: String,
            count: Int = 1
        ): ShoppingItem {
            return ShoppingItem(
                id = UUID.randomUUID().toString(),
                name = name,
                count = count,
                added = Date(),
                checked = false,
            )
        }
    }

    fun increase(): ShoppingItem {
        return copy(count = count + 1)
    }

    fun decrease(): ShoppingItem {
        return copy(count = max(count - 1, 0))
    }

    fun changeChecked(checked: Boolean): ShoppingItem {
        return copy(checked = checked)
    }
}
