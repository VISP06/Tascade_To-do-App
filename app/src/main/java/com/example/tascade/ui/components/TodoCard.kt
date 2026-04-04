package com.example.tascade.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.model.Todo
import com.example.tascade.ui.theme.BebasNeue

@Composable
fun TodoCard(task: Todo, modifier:Modifier = Modifier){
    Box(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
            .padding(start = 5.dp, top = 5.dp)
            .fillMaxWidth(0.9f)

    ){
        Box(
            Modifier
                .offset(x = -4.dp, y = -4.dp)
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth() //the outer container has determined the width, we just fill into it here
                .padding(vertical = 16.dp) //replaced .size() allowing for bigger text to be placed
        ){
            Text(
                text = stringResource(task.title), //requires only string resources?
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp,
                fontFamily = BebasNeue
            )
        }
    }
}

