package com.example.tascade.ui.pomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
fun PomodoroBoard(modifier:Modifier = Modifier, timeString:String, pomodoroViewModel: PomodoroViewModel){
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if(pomodoroViewModel.isWorkSession.collectAsState().value) "Work Session" else "Break Time",
                    color = Color(0xFF7B1FA2),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = BebasNeue,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
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
}