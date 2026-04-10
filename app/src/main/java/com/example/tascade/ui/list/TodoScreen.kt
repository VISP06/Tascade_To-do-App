package com.example.tascade.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoBottomBar
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.ui.components.TodoFAB
import com.example.tascade.ui.components.TodoTopBar
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground

val vm = TodoViewModel()

@Composable
fun TodoApp() {
    Scaffold(
        topBar = { TodoTopBar() },
        bottomBar = { TodoBottomBar() },
        floatingActionButton = { TodoFAB() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {

            TodoList(vm.todos, contentPaddingValues = innerPadding)

        }
    }
}

@Composable
fun TodoList(
    tasks: List<Todo>,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues
) {
    if (tasks.isEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.part_1_illustration),
                contentDescription = null

            )
            Image(
                painter = painterResource(R.drawable.part_2_illustration),
                contentDescription = null
            )
        }
    } else {
        LazyColumn(modifier = Modifier, contentPadding = contentPaddingValues) {
            items(items = tasks) { task ->
                Spacer(Modifier.padding(8.dp))
                TodoCard(
                    task = task,
                    modifier = Modifier.animateItem()
                )
            }
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


