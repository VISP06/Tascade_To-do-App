package com.example.tascade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.ui.theme.BebasNeue

//under 3 button navigation this button is buried underneath the 3 buttons
@Preview
@Composable
fun TodoBottomBar(){

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    onClick = {

                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    border = BorderStroke(3.dp, color = Color.Black),
                    modifier = Modifier
                        .shadow(
                            elevation = 6.dp,
                            shape = RectangleShape,
                            ambientColor = Color.Black.copy(alpha = 0.3f),
                            spotColor = Color.Black.copy(alpha = 0.3f)
                        )
                        .fillMaxWidth(0.8f)
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                ) {
                    Text(
                        text = "Clear Completed Tasks",
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = BebasNeue
                    )
                }
            }
}