package com.moriatsushi.performance.drag

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round

@Composable
fun DragScreen(
    modifier: Modifier = Modifier
) {
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        DragItem(
            offset = offset,
            onChangeOffset = {
                offset = it
            }
        )
    }

    // It is intentionally slowed down for performance verification
    Thread.sleep(50)
}

@Composable
fun DragItem(
    offset: Offset,
    onChangeOffset: (Offset) -> Unit,
    modifier: Modifier = Modifier
) {
    val updatedValue by rememberUpdatedState(offset)

    Box(
        modifier = modifier
            .offset { updatedValue.round() }
            .background(Color.Green)
            .size(50.dp)
            .pointerInput(Unit) {
                forEachGesture {
                    detectDragGestures { _, dragAmount ->
                        onChangeOffset(updatedValue + dragAmount)
                    }
                }
            }
    )
}
