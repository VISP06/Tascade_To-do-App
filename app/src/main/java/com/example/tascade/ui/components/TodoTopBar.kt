package com.example.tascade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.R
import com.example.tascade.ui.theme.BebasNeue

@Composable
fun TodoTopBar(modifier: Modifier = Modifier, topBarHeadingId:Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color(0xFF7B1FA2))
            .border(width = 3.dp, color = Color.Black)
            .padding(start = 36.dp, top = 40.dp)
    ) {

        Text(
            text = stringResource(topBarHeadingId),
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 5.sp,
            fontSize = 64.sp,
            fontFamily = BebasNeue,
            modifier = modifier

        )
    }
}