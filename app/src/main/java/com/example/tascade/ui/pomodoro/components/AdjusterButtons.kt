package com.example.tascade.ui.pomodoro.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun IncrementButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    var interactionSource = remember { MutableInteractionSource() }
    val isTapped by interactionSource.collectIsPressedAsState()
    val pressAnimationScale by animateDpAsState(
        targetValue = if(isTapped) 0.dp else -4.dp
    )
    Box(
        modifier = modifier
            .height(40.dp)
            .aspectRatio(1f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
    ) {
        Box(
            modifier
                .height(40.dp)
                .offset(x = pressAnimationScale, y = pressAnimationScale)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun DecrementButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    var interactionSource = remember { MutableInteractionSource() }
    val isTapped by interactionSource.collectIsPressedAsState()
    val pressAnimationScale by animateDpAsState(
        targetValue = if(isTapped) 0.dp else -4.dp
    )
    Box(
        modifier = modifier
            .height(40.dp)
            .aspectRatio(1f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
    ) {
        Box(
            modifier
                .height(40.dp)
                .offset(x = pressAnimationScale, y = pressAnimationScale)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = Color.Black
            )
        }
    }
}