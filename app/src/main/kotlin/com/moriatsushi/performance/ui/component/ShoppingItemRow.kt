package com.moriatsushi.performance.ui.component

import android.text.format.DateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriatsushi.performance.model.ShoppingItem
import java.util.*

@Composable
fun ShoppingItemRow(
    item: ShoppingItem,
    onClickIncrease: () -> Unit,
    onClickDecrease: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.checked,
            onCheckedChange = onCheckedChange
        )
        Column(
            modifier = Modifier.weight(1F)
        ) {
            Text(
                text = item.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Added: ${item.added.formatted()}",
                fontSize = 11.sp
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = onClickDecrease) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease"
            )
        }
        Box(
            modifier = Modifier
                .widthIn(min = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item.count.formatted())
        }
        IconButton(onClick = onClickIncrease) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "Increase"
            )
        }
    }
}

private fun Date.formatted(): String {
    return DateFormat.format("yyyy/MM/dd", this).toString()
}

private fun Int.formatted(): String {
    return String.format("%,d", this)
}
