package com.example.tascade.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun TodoFAB(modifier: Modifier = Modifier, onAddClicked:(titleInput:String)->Unit) {
    var showAddDialog by remember { mutableStateOf<Boolean>(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateDpAsState(
        targetValue = if(isPressed) 0.dp else -4.dp
    )

    if (showAddDialog) {
        AddTodoDialog(showAddDialog, onDismiss = { showAddDialog = false }, onAddClicked = onAddClicked)
    }
    Box(
        Modifier
            .clip(CircleShape)
            .background(Color.Black)
            .offset(x = scale, y = scale)
    ) {
        LargeFloatingActionButton(
            onClick = {
                showAddDialog = true
            },
            shape = CircleShape,
            containerColor = Color(0xFF1A237E),
            interactionSource = interactionSource,
            modifier = Modifier
                .border(3.dp, Color.Black, shape = CircleShape)
        ) {
            Icon(
                Icons.Filled.Add,
                "Floating action button.",
                modifier = Modifier
                    .size(50.dp),
                tint = Color.White
            )
        }
    }
}