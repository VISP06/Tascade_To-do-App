package com.example.tascade.ui.pomodoro

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.PomodoroViewModel
import com.example.tascade.R
import com.example.tascade.ui.pomodoro.components.DecrementButton
import com.example.tascade.ui.pomodoro.components.IncrementButton
import com.example.tascade.ui.pomodoro.components.PauseButton
import com.example.tascade.ui.pomodoro.components.PomodoroBoard
import com.example.tascade.ui.pomodoro.components.ResetButton
import com.example.tascade.ui.pomodoro.components.StartButton
import com.example.tascade.ui.pomodoro.components.TimeAdjusterBlock
import com.example.tascade.ui.theme.BebasNeue
import com.example.tascade.util.formatTimeString
import com.example.tascade.util.halftoneBackground

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
            soundPool.play(alarmSoundId, 1f, 1f, 1, 0, 1f)
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