package com.moriatsushi.performance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

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
    onAddRequest: (name: String) -> Unit
): AddDialogValues {
    var name by rememberSaveable { mutableStateOf("") }

    return remember(name, onDismissRequest, onAddRequest) {
        AddDialogValues(
            name = name,
            onChangeName = {
                name = it
            },
            onClickAdd = {
                onAddRequest(name)
            },
            onDismissRequest = onDismissRequest
        )
    }
}

data class AddDialogValues(
    val name: String,
    val onChangeName: (String) -> Unit,
    val onClickAdd: () -> Unit,
    val onDismissRequest: () -> Unit,
)
