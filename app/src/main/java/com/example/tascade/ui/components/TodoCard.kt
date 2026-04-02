package com.example.tascade.ui.components


import android.graphics.Paint
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tascade.ui.theme.TascadeTheme

@Composable
fun TodoCard(modifier:Modifier = Modifier){
    Box(
        Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
            .padding(start = 4.dp, top = 5.dp)
    ){
        Box(
            Modifier
                .offset(x = -4.dp, y = -4.dp)
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                .size(width = 150.dp, height = 30.dp)
        ){
            Text("Hello",
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun TodoCardPreview(){
    TascadeTheme {
        TodoCard()
    }
}