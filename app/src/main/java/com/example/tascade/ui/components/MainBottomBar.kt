package com.example.tascade.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.ui.theme.BebasNeue

enum class AppScreen { TASKS, TIMER }
@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier
){
    var currentScreen by remember { mutableStateOf(AppScreen.TASKS) }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF2C300))
            .navigationBarsPadding()
            .height(80.dp)
            .border(width = 3.dp, color = Color.Black)
            .padding(start = 36.dp, top = 40.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
        ){
            AnimatedContent(
                targetState = (currentScreen == AppScreen.TASKS),

            ){ isActive->
                if(isActive){
                    TaskIconFocused(onTaskClick = { currentScreen = AppScreen.TASKS}, )
                }else{
                    TaskIconDefault()
                }
            }
            AnimatedContent(
                targetState = (currentScreen == AppScreen.TIMER)
            ) { isActive->
                if(isActive){
                    TimerIconFocused(onTimerClick = {currentScreen = AppScreen.TIMER})
                }else{
                    TimerIconDefault()
                }

            }
        }

    }
}

@Preview
@Composable
fun TaskIconFocused(modifier:Modifier = Modifier, onTaskClick:()->Unit) {

    Box(
        modifier
            .height(80.dp)
            .clip(shape = RectangleShape)
            .background(Color.Black)
            .aspectRatio(1f)
            .clickable(
                onClick = {
                    onTaskClick()
                }
            )
    ) {
        Box(

            Modifier
                .fillMaxSize()
                .offset(x = (-4).dp, y = (-4).dp)
                .background(color = Color(0xFF7B1FA2), shape = RectangleShape)
                .border(2.dp, color = Color.Black),
            contentAlignment = Alignment.Center

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
                Text(
                    text = "TASKS",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = BebasNeue
                    )
                )
            }
        }
    }
}

@Composable
fun TimerIconFocused(modifier: Modifier = Modifier, onTimerClick:()->Unit) {

    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(1f) //helps in making the square shape
            .background(Color.Black)
            .clickable(
                onClick = {
                    onTimerClick()
                }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (-4).dp, y = (-4).dp)
                .background(Color(0xFF7B1FA2))
                .border(width = 3.dp, color = Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "TIMER",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        fontFamily = BebasNeue
                    )
                )
            }
        }
    }
}

@Composable
fun TimerIconDefault(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(1f)
            .background(color = Color(0xFFF2C300))
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Text(
                text = "Timer",
                color = Color.Black,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = BebasNeue,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun TaskIconDefault(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(1f)
            .background(color = Color(0xFFF2C300))
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Text(
                text = "TASKS",
                color = Color.Black,
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontFamily = BebasNeue,
                    fontSize = 12.sp
                )
            )
        }
    }
}