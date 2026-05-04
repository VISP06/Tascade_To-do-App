package com.example.tascade.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun PomodoroScreen(globalPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(globalPadding)
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Text("Pomodoro Timer Goes Here", fontSize = 24.sp)
    }
}