package com.moriatsushi.performance.mutablelist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MutableLazyListScreen(
    modifier: Modifier = Modifier
) {
    val list = remember { mutableStateListOf<String>() }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            AddButton(
                onClick = { list.add(0, "Item: ${list.size + 1}") }
            )
        }
        items(
            items = list,
            key = { it }
        ) {
            Item(label = it)
        }
    }
}

@Composable
private fun AddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.padding(horizontal = 16.dp),
        onClick = { onClick() }
    ) {
        Text(text = "Add Item")
    }
}

@Composable
private fun Item(
    label: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(16.dp),
        text = label
    )

    // It is intentionally slowed down for performance verification
    Thread.sleep(50)
}
