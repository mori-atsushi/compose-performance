package com.moriatsushi.performance.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moriatsushi.performance.model.ShoppingItem
import com.moriatsushi.performance.ui.component.AddDialog
import com.moriatsushi.performance.ui.component.AddDialogValues
import com.moriatsushi.performance.ui.component.ShoppingItemRow
import com.moriatsushi.performance.ui.component.rememberAddDialogValues

@Composable
fun ShoppingListPage(
    modifier: Modifier = Modifier,
    viewModel: ShoppingListViewModel = viewModel()
) {
    val list by viewModel.list.collectAsState()
    var isVisibleAddDialog by rememberSaveable { mutableStateOf(false) }
    val addDialogValues = if (isVisibleAddDialog) {
        rememberAddDialogValues(
            onDismissRequest = {
                isVisibleAddDialog = false
            },
            onAddRequest = { name, count ->
                viewModel.add(name, count)
                isVisibleAddDialog = false
            }
        )
    } else {
        null
    }

    ShoppingListPage(
        list = list,
        addDialogValues = addDialogValues,
        onClickIncrease = {
            viewModel.increase(it)
        },
        onClickDecrease = {
            viewModel.decrease(it)
        },
        onCheckedChange = { item, checked ->
            viewModel.changeChecked(item, checked)
        },
        onClickAdd = {
            isVisibleAddDialog = true
        },
        modifier = modifier,
    )
}

@Composable
fun ShoppingListPage(
    list: List<ShoppingItem>,
    addDialogValues: AddDialogValues?,
    onClickIncrease: (ShoppingItem) -> Unit,
    onClickDecrease: (ShoppingItem) -> Unit,
    onCheckedChange: (ShoppingItem, Boolean) -> Unit,
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val isTop = listState.firstVisibleItemIndex == 0 &&
            listState.firstVisibleItemScrollOffset == 0
    val sorted = list.sorted()

    if (addDialogValues != null) {
        AddDialog(addDialogValues)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        ShoppingListTopAppBar(
            elevation = if (isTop) 0.dp else 4.dp,
            onClickAdd = onClickAdd,
        )
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = listState
        ) {
            items(sorted) { item ->
                ShoppingItemRow(
                    item = item,
                    onClickIncrease = {
                        onClickIncrease(item)
                    },
                    onClickDecrease = {
                        onClickDecrease(item)
                    },
                    onCheckedChange = {
                        onCheckedChange(item, it)
                    }
                )
            }
        }
    }
}

@Composable
private fun ShoppingListTopAppBar(
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = elevation,
        actions = {
            IconButton(onClick = onClickAdd) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        title = {
            Text(
                text = "Shopping List",
                color = MaterialTheme.colors.onBackground
            )
        }
    )
}

private fun List<ShoppingItem>.sorted(): List<ShoppingItem> {
    return sortedWith(
        compareBy(
            { it.checked },
            { -it.added.time }
        )
    )
}

