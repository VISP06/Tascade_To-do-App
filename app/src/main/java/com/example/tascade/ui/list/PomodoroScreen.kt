package com.example.tascade.ui.list

import android.media.SoundPool
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.PomodoroViewModel
import com.example.tascade.R
import com.example.tascade.ui.theme.BebasNeue
import com.example.tascade.util.halftoneBackground

//helper method to convert raw time (eg. 1500) into proper formatted strings (eg. 25:00)
fun formatTimeString(time:Int):String{
    val timeInMinutes = time / 60
    val timeInSeconds = time % 60
    return String.format("%02d : %02d", timeInMinutes, timeInSeconds)
}
@Composable
fun PomodoroScreen(
    globalPadding: PaddingValues,
    modifier:Modifier = Modifier,
    pomodoroViewModel: PomodoroViewModel
) {
    val time by pomodoroViewModel.timerValue.collectAsState()
    val workTime by pomodoroViewModel.workDuration.collectAsState()
    val breakTime by pomodoroViewModel.breakDuration.collectAsState()

    val timeString = formatTimeString(time)

    val context = LocalContext.current
    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(5)
            .build()
    }
    val alarmSoundId = remember {
        soundPool.load(context, R.raw.pomodoro_alarm, 1)
    }

    LaunchedEffect(time) {
        if(time == 0)
            soundPool.play(alarmSoundId, 1f, 1f, 1, 5, 1f)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(globalPadding)
            .background(Color(0xFFFACC15))
            .halftoneBackground(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            PomodoroBoard(timeString = timeString, pomodoroViewModel = pomodoroViewModel)
            Spacer(
                modifier = modifier.padding(12.dp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TimeAdjusterBlock(
                    boxName = "WORK",
                    timeValue = workTime,
                    decrementButton = { DecrementButton(onClick = { pomodoroViewModel.decreaseWorkTime() }) },
                    incrementButton = { IncrementButton(onClick = { pomodoroViewModel.increaseWorkTime() }) }
                )

                TimeAdjusterBlock(
                    boxName = "BREAK",
                    timeValue = breakTime,
                    decrementButton = { DecrementButton(onClick = { pomodoroViewModel.decreaseBreakTime() }) },
                    incrementButton = { IncrementButton(onClick = { pomodoroViewModel.increaseBreakTime() }) }
                )
            }
            Spacer(
                modifier = modifier.padding(12.dp)
            )
            AnimatedContent(
                targetState = pomodoroViewModel.isRunning.collectAsState().value
            ) { isActive -> //"extremely smart" solution of just adding ! to the isActive in if condition helps my app to work as intended
                if(!isActive){
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
@Composable
fun TimeAdjusterBlock(
    boxName:String,
    timeValue: Int,
    decrementButton: @Composable () -> Unit,
    incrementButton: @Composable () -> Unit
) {
    val timeInMins = timeValue / 60
    val timeString = String.format("%02d", timeInMins)

    Box(
        modifier = Modifier
            .height(80.dp)
            .aspectRatio(2f)
            .background(color = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                decrementButton()

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = boxName,
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = BebasNeue,
                            fontWeight = FontWeight.Thin
                        )
                    )
                    Text(
                        text = timeString,
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = BebasNeue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))

                incrementButton()
            }
        }
    }
}

@Composable
fun IncrementButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
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
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
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
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color.White)
                .border(color = Color.Black, shape = RectangleShape, width = 2.dp)
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
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