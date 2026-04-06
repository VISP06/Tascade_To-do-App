package com.example.tascade.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground

val vm = TodoViewModel()
@Composable
fun TodoApp(){
    Scaffold(
        topBar = {TodoTopBar()},
        floatingActionButton = { TodoFAB() }
    ) { innerPadding->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {

            TodoList(vm.todos, contentPaddingValues =innerPadding)

        }
    }
}

@Composable
fun TodoList(tasks:List<Todo>, modifier: Modifier = Modifier, contentPaddingValues: PaddingValues){

    LazyColumn(modifier = Modifier, contentPadding=contentPaddingValues) {
        items(items = tasks, key = { task -> task.id }){ task->
            Spacer(Modifier.padding(8.dp))
            TodoCard(
                task = task,
                modifier = Modifier.animateItem()
            )
        }
    }
}
@Composable
fun TodoTopBar(modifier:Modifier = Modifier){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color(0xFF7B1FA2))
            .border(width = 3.dp, color = Color.Black)
            .padding(start = 36.dp, top = 40.dp)
    ){

        Text(
            text = stringResource(R.string.top_bar_title),
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 5.sp,
            fontSize = 64.sp,
            modifier = modifier

        )
    }
}

@Composable
fun TodoFAB(modifier:Modifier = Modifier) {
    Box(
        Modifier
            .clip(CircleShape)
            .background(Color.Black)
            .offset(x = -4.dp, y = -4.dp)
    ) {
        LargeFloatingActionButton(
            onClick = { vm.addTodo()},
            shape = CircleShape,
            containerColor = Color(0xFF1A237E),
            modifier = Modifier.border(3.dp, Color.Black, shape = CircleShape)
        ) {
            Icon(
                Icons.Filled.Add,
                "Floating action button.",
                modifier = Modifier
                    .size(50.dp),
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TascadeTheme {
        TodoApp()
    }
}


