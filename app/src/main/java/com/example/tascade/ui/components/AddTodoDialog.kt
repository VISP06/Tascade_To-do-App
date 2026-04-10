package com.example.tascade.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tascade.ui.list.vm

@Composable
fun AddTodoDialog(showDialog: Boolean, onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    var userTodoInput by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black)
                .padding(start = 5.dp, top = 5.dp)
                .size(300.dp, 350.dp)

        ) {
            Column(
                Modifier
                    .offset(x = -4.dp, y = -4.dp)
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .padding(16.dp) //applies "breathing room" (Gap) to all 4 sides hence why fillMaxWidth in textfield was the right move
            ) {
                Text(
                    text = "New_Task",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = userTodoInput,
                    onValueChange = { userTodoInput = it },
                    label = { Text("Task Description") },
                    modifier = Modifier
                        .border(width = 3.dp, color = Color.Black)
                        .background(color = Color.DarkGray)
                        .weight(1f)
                )

                Spacer(Modifier.weight(0.1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        border = BorderStroke(3.dp, color = Color.Black)
                    ){
                        Text(
                            text = "Cancel",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            vm.addTodo(userTodoInput)
                            onDismiss()
                        },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A237E)),
                        border = BorderStroke(3.dp, color = Color.Black)
                    ){
                        Text(
                            text = "Add Task",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}