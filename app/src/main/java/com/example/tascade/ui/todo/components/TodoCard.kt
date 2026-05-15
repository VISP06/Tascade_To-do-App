package com.example.tascade.ui.todo.components


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.R
import com.example.tascade.model.Todo
import com.example.tascade.ui.theme.BebasNeue


@Composable
fun TodoCard(
    task: Todo,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier,
    isCurrentlyChecked: Boolean,

) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateDpAsState(
        targetValue = if(isPressed) 0.dp else -4.dp
    )
    Box(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
            .padding(start = 5.dp, top = 5.dp)
            .fillMaxWidth(0.9f)
    ) {
        Box(

            Modifier
                .offset(x = scale, y = scale)
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                .background(
                    color = if(isCurrentlyChecked) Color.LightGray else Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TodoCheckBox(onCheckedChange = onCheckedChange, isCurrentlyChecked = isCurrentlyChecked, interactionSource = interactionSource)
                    Text(
                        text = task.title,
                        color = Color(0xFF1A237E),
                        fontSize = 32.sp,
                        fontFamily = BebasNeue,
                        textDecoration = if(isCurrentlyChecked) TextDecoration.LineThrough else null
                    )
            }
        }
    }
}

@Composable
fun TodoCheckBox(modifier:Modifier = Modifier, onCheckedChange: () -> Unit, isCurrentlyChecked:Boolean, interactionSource: MutableInteractionSource){

    Box(
        modifier = Modifier
            .size(28.dp)
            .border(width = 3.dp, color = Color.Black)
            .background(
                color = if (isCurrentlyChecked) {
                    Color.Yellow

                } else {
                    Color.White
                }
            )
            .clickable(
                onClick = {
                    onCheckedChange()
                },
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ){
        if(isCurrentlyChecked) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(18.dp)
            )

        }
    }
}

/*
Arrangement.spacedBy(dp)	When you want a uniform gap between every item in a list/column.
Spacer(Modifier.height(dp))	When you need a specific, one-off gap between two specific elements.
Modifier.weight(1f)	        When you want an element (or a Spacer) to expand and push everything else to the edges of the screen.
Padding	                    Use this for internal breathing room (e.g., space between the edge of the dialog and the content).
 */
