package com.moriatsushi.performance.mutablelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MutableListScreen(
    modifier: Modifier = Modifier
) {
    val list = remember { mutableStateListOf<String>() }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AddButton(
            onClick = { list.add(0, "Item: ${list.size + 1}") }
        )
        list.forEach {
            key(it) {
                Item(label = it)
            }
        }
    }
}

@Composable
fun AddButton(
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
fun Item(
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
