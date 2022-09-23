package com.moriatsushi.performance.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.performance.model.ShoppingItem
import com.moriatsushi.performance.ui.component.ShoppingItemRow
import java.util.*

private val items = List(100) {
    ShoppingItem(
        name = "Item:$it",
        count = it,
        added = Date()
    )
}

@Composable
fun ShoppingListPage(
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val isTop = listState.firstVisibleItemIndex == 0 &&
            listState.firstVisibleItemScrollOffset == 0

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TopAppBar(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.background,
            elevation = if (isTop) 0.dp else 4.dp
        ) {
            Text(
                text = "List Sample",
                color = MaterialTheme.colors.onBackground
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth(),
            state = listState
        ) {
            items(items) {
                ShoppingItemRow(
                    item = it
                )
            }
        }
    }
}
