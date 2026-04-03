package com.example.tascade.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

fun Modifier.halftoneBackground(
    dotColor: Color = Color.Black.copy(alpha = 0.15f), // Adjust transparency here
    dotRadius: Float = 3f, // Size of the dot
    spacing: Float = 30f  // Distance between dots
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