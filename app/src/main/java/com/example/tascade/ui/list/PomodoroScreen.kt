package com.example.tascade.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.ui.theme.BebasNeue
import com.example.tascade.util.halftoneBackground

@Preview
@Composable
fun PomodoroScreen(globalPadding: PaddingValues, modifier:Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(globalPadding)
            .background(Color(0xFFFACC15))
            .halftoneBackground(),
        contentAlignment = Alignment.Center
    ) {
        Column(){
            StartButton()
            Spacer(
                modifier = modifier.padding(12.dp)
            )
            ResetButton()
        }
    }
}


@Composable
fun StartButton(modifier:Modifier = Modifier){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {

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
                ),

            )
        }
    }
}

@Composable
fun ResetButton(modifier:Modifier = Modifier){
    Box(
        modifier = modifier
            .height(80.dp)
            .aspectRatio(3f)
            .background(color = Color.Black)
            .fillMaxWidth(0.9f)
            .clickable(
                onClick = {

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