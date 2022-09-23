package com.moriatsushi.performance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlin.math.roundToInt

@Composable
fun AddDialog(
    values: AddDialogValues,
) {
    Dialog(onDismissRequest = values.onDismissRequest) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background, RoundedCornerShape(8.dp))
                .padding(21.dp)
        ) {
            Text(
                text = "Add to shopping list",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = values.name,
                onValueChange = values.onChangeName,
                placeholder = {
                    Text(text = "Name")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Count: ${values.count.roundToInt()}",
                fontSize = 16.sp,
            )
            Slider(
                value = values.count,
                onValueChange = { values.onChangeCount(it) },
                valueRange = 1f..12f
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = values.onClickAdd
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun rememberAddDialogValues(
    onDismissRequest: () -> Unit,
    onAddRequest: (name: String, count: Int) -> Unit
): AddDialogValues {
    var name by rememberSaveable { mutableStateOf("") }
    var count by rememberSaveable { mutableStateOf(1f) }

    return remember(name, count, onDismissRequest, onAddRequest) {
        AddDialogValues(
            name = name,
            count = count,
            onChangeName = { name = it },
            onChangeCount = { count = it },
            onClickAdd = { onAddRequest(name, count.roundToInt()) },
            onDismissRequest = onDismissRequest
        )
    }
}

data class AddDialogValues(
    val name: String,
    val count: Float,
    val onChangeName: (String) -> Unit,
    val onChangeCount: (Float) -> Unit,
    val onClickAdd: () -> Unit,
    val onDismissRequest: () -> Unit,
)
