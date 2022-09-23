package com.moriatsushi.performance.ui

import androidx.lifecycle.ViewModel
import com.moriatsushi.performance.model.ShoppingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private val initialList = listOf(
    ShoppingItem.create(name = "Eggs", count = 10),
    ShoppingItem.create(name = "Milk", count = 1),
    ShoppingItem.create(name = "Potatoes", count = 4),
    ShoppingItem.create(name = "Carrots", count = 1),
    ShoppingItem.create(name = "Onion", count = 1),
    ShoppingItem.create(name = "Beef", count = 1),
    ShoppingItem.create(name = "Curry roux", count = 1),
    ShoppingItem.create(name = "Tomato", count = 3),
    ShoppingItem.create(name = "Cucumber", count = 1),
    ShoppingItem.create(name = "Lettuce", count = 1),
    ShoppingItem.create(name = "Bread", count = 2),
    ShoppingItem.create(name = "Chicken", count = 2),
    ShoppingItem.create(name = "Soy sauce", count = 1),
    ShoppingItem.create(name = "Salt", count = 1),
    ShoppingItem.create(name = "Pepper", count = 1),
)

class ShoppingListViewModel : ViewModel() {
    private val _list = MutableStateFlow(initialList)
    val list: StateFlow<List<ShoppingItem>> = _list

    fun increase(item: ShoppingItem) {
        _list.update { list ->
            list.map {
                if (it.id == item.id) {
                    it.increase()
                } else {
                    it
                }
            }
        }
    }

    fun decrease(item: ShoppingItem) {
        _list.update { list ->
            list.map {
                if (it.id == item.id) {
                    it.decrease()
                } else {
                    it
                }
            }
        }
    }

    fun changeChecked(item: ShoppingItem, checked: Boolean) {
        _list.update { list ->
            list.map {
                if (it.id == item.id) {
                    it.changeChecked(checked)
                } else {
                    it
                }
            }
        }
    }

    fun add(name: String, count: Int) {
        val new = ShoppingItem.create(
            name = name,
            count = count,
        )
        _list.update { list ->
            list.toMutableList()
                .apply { add(0, new) }
                .toList()
        }
    }
}
