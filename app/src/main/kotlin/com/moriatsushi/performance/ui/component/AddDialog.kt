package com.moriatsushi.performance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlin.math.roundToInt

@Composable
fun AddDialog(
    state: AddDialogState,
) {
    Dialog(onDismissRequest = state.onDismissRequest) {
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
                value = state.name,
                onValueChange = state::onChangeName,
                placeholder = {
                    Text(text = "Name")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Count: ${state.count.roundToInt()}",
                fontSize = 16.sp,
            )
            Slider(
                value = state.count,
                onValueChange = { state.onChangeCount(it) },
                valueRange = 1f..12f
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = state::onClickAdd
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
): AddDialogState {
    return remember(onDismissRequest, onAddRequest) {
        AddDialogState(onDismissRequest, onAddRequest)
    }
}

@Stable
class AddDialogState(
    val onDismissRequest: () -> Unit,
    private val onAddRequest: (name: String, count: Int) -> Unit
) {
    var name by mutableStateOf("")
        private set
    var count by mutableStateOf(1f)
        private set

    fun onChangeName(name: String) {
        this.name = name
    }

    fun onChangeCount(count: Float) {
        this.count = count
    }

    fun onClickAdd() {
        onAddRequest(name, count.roundToInt())
    }
}
