package com.example.tascade.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tascade.R
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.util.halftoneBackground
import com.example.tascade.util.sampleTasks

@Preview
@Composable
fun TodoApp(){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFACC15))
            .halftoneBackground(),
        contentAlignment = Alignment.Center

    ){

        TodoList(sampleTasks)

    }
}

@Composable
fun TodoList(tasks:List<Todo>, modifier: Modifier = Modifier){
    LazyColumn(modifier = Modifier) {
        items(tasks){ sampleTask->
            TodoCard(sampleTask)
            Spacer(Modifier.padding(8.dp))
        }
    }
}