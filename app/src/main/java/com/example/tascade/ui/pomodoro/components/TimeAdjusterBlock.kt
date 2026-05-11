package com.example.tascade.ui.pomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.ui.theme.BebasNeue

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