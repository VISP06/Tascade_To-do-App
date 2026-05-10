package com.example.tascade.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//extension function which is called in the app composable to make background (apply dots)
fun Modifier.halftoneBackground(
    dotColor: Color = Color.Black.copy(alpha = 0.15f),
    dotRadius: Float = 3f, //Size of the dot
    spacing: Float = 30f  //Distance between dots
) = this.drawBehind {
    val rows = (size.height / spacing).toInt()
    val cols = (size.width / spacing).toInt()

    for (r in 0..rows) {
        for (c in 0..cols) {
            drawCircle(
                color = dotColor,
                radius = dotRadius,
                center = Offset(c * spacing, r * spacing)
            )
        }
    }
}