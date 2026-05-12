package com.example.tascade.ui.pomodoro.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Expand
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.PomodoroViewModel
import com.example.tascade.ui.theme.BebasNeue

@Composable
fun FullScreenMode(
    timeString:String,
    pomodoroViewModel: PomodoroViewModel,
    modifier:Modifier = Modifier,
    onExpand: () -> Unit
){
    val sessionText = if (pomodoroViewModel.isWorkSession.collectAsState().value) "Work Session" else "Break Time"
    Box(
        modifier = modifier.fillMaxSize().background(color = Color.White)
    ){
        Box(
            modifier = modifier.
        ){
            Box(){
                modifier = modifier.offset(x = (-4).dp, y = (-4).dp)
            }
        }
    }
}