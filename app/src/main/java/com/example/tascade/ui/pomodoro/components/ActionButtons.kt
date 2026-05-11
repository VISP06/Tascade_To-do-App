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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
fun StartButton(
    modifier:Modifier = Modifier,
    pomodoroViewModel: PomodoroViewModel
){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {
                    pomodoroViewModel.startTimer()
                }
            )
    ){
        Box(
            modifier
                .height(80.dp)
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color(0xFF1A237E))
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "START",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = BebasNeue
                )
            )
        }
    }
}

@Composable
fun PauseButton(
    modifier: Modifier = Modifier,
    pomodoroViewModel: PomodoroViewModel
){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {
                    pomodoroViewModel.pauseTimer()
                }
            )
    ){
        Box(
            modifier
                .height(80.dp)
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color.Red)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "PAUSE",
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = BebasNeue
                )
            )
        }
    }
}

@Composable
fun ResetButton(
    modifier:Modifier = Modifier,
    pomodoroViewModel: PomodoroViewModel
){
    var interactionSource = remember { MutableInteractionSource() }
    val isTapped by interactionSource.collectIsPressedAsState()
    val pressAnimationScale by animateDpAsState(
        targetValue = if(isTapped) 0.dp else -4.dp
    )
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {
                    pomodoroViewModel.resetTimer()
                },
                interactionSource = interactionSource
            )
    ){
        Box(
            modifier
                .height(80.dp)
                .offset(x = pressAnimationScale, y = pressAnimationScale)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = "RESET",
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    fontFamily = BebasNeue
                )
            )
        }
    }
}