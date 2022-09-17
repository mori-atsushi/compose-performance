package com.moriatsushi.performance.carousel

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarouselScreen(
    modifier: Modifier = Modifier
) {
    val list = remember {
        mutableStateListOf<CarouselValues>().apply {
            addAll(List(30) { CarouselValues() })
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        list.forEachIndexed { index, item ->
            Carousel(
                values = item,
                onClickAddButton = {
                    list[index] = item.added()
                }
            )
        }
    }
}

@Composable
fun Carousel(
    values: CarouselValues,
    onClickAddButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        values.items.forEach {
            Text(text = it)
        }
        Button(
            onClick = onClickAddButton
        ) {
            Text(text = "Add Item")
        }
    }

    // It is intentionally slowed down for performance verification
    Thread.sleep(50)
}

@Immutable
data class CarouselValues(
    val items: List<String> = emptyList()
) {
    fun added(): CarouselValues {
        return copy(items = items + "Item: ${items.size + 1}")
    }
}
