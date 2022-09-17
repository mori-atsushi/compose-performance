package com.moriatsushi.performance.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val items = List(100) {
    "Item:$it"
}

@Composable
fun ListScreen(
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val isTop = listState.firstVisibleItemIndex == 0 &&
            listState.firstVisibleItemScrollOffset == 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        ListTopAppBar(
            enabledShadow = isTop
        )
        LazyColumn(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth(),
            state = listState
        ) {
            items(items) {
                Item(label = it)
            }
        }
    }

    // It is intentionally slowed down for performance verification
    Thread.sleep(50)
}

@Composable
private fun ListTopAppBar(
    modifier: Modifier = Modifier,
    enabledShadow: Boolean = false
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = if (enabledShadow) 0.dp else 4.dp
    ) {
        Text(
            text = "List Sample",
            color = MaterialTheme.colors.onBackground
        )
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
}
