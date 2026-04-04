package com.example.tascade.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.R
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.util.halftoneBackground
import com.example.tascade.util.sampleTasks


@Preview
@Composable
fun TodoApp(){
    Scaffold(
        topBar = {TodoTopBar()}
    ) { innerPadding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {

            TodoList(sampleTasks, contentPaddingValues =innerPadding)

        }
    }
}

@Composable
fun TodoTopBar(modifier:Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color.Magenta)
            .border(width = 3.dp, color = Color.Black)
    ){
        Text(
            text = stringResource(R.string.top_bar_title),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 64.sp,
            modifier = modifier.rotate(-2f)
        )
    }
}

@Composable
fun TodoList(tasks:List<Todo>, modifier: Modifier = Modifier, contentPaddingValues: PaddingValues){
    LazyColumn(modifier = Modifier, contentPadding=contentPaddingValues) {
        items(tasks){ sampleTask->
            TodoCard(sampleTask)
            Spacer(Modifier.padding(8.dp))
        }
    }
}