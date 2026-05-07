package com.example.tascade.ui.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.PomodoroViewModel
import com.example.tascade.ui.theme.BebasNeue
import com.example.tascade.util.halftoneBackground

fun formatTimeString(time:Int):String{
    val timeInMinutes = time / 60
    val timeInSeconds = time % 60
    return String.format("%02d : %02d", timeInMinutes, timeInSeconds)
}
@Composable
fun PomodoroScreen(globalPadding: PaddingValues, modifier:Modifier = Modifier, pomodoroViewModel: PomodoroViewModel) {
    val time by pomodoroViewModel.timerValue.collectAsState()
    var timeString = formatTimeString(time)
    val isCounting = pomodoroViewModel.isRunning.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(globalPadding)
            .background(Color(0xFFFACC15))
            .halftoneBackground(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            PomodoroBoard(timeString = timeString)
            Spacer(
                modifier = modifier.padding(12.dp)
            )
            AnimatedContent(
                targetState = isCounting.value
            ) { isActive ->
                if(isActive){
                    StartButton(pomodoroViewModel = pomodoroViewModel)
                }else{
                    PauseButton(pomodoroViewModel = pomodoroViewModel)
                }
            }

            Spacer(
                modifier = modifier.padding(12.dp)
            )
            ResetButton(pomodoroViewModel = pomodoroViewModel)
        }
    }
}


@Composable
fun StartButton(modifier:Modifier = Modifier, pomodoroViewModel: PomodoroViewModel){
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
fun PauseButton(modifier: Modifier = Modifier, pomodoroViewModel: PomodoroViewModel){
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
fun ResetButton(modifier:Modifier = Modifier, pomodoroViewModel: PomodoroViewModel){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {
                    pomodoroViewModel.resetTimer()
                }
            )
    ){
        Box(
            modifier
                .height(80.dp)
                .offset(x = (-4).dp, y = (-4).dp)
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

@Preview
@Composable
fun PomodoroBoard(modifier:Modifier = Modifier, timeString:String){
    Box(
        modifier = modifier
            .height(250.dp)
            .aspectRatio(1.5f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
    ){
        Box(
            modifier
                .height(250.dp)
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ){
            Text(
                text = timeString,
                color = Color.Black,
                style = TextStyle(
                    fontSize = 100.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = BebasNeue
                )
            )
        }
    }
}