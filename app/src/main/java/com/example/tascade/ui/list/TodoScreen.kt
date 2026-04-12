package com.example.tascade.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoBottomBar
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.ui.components.TodoFAB
import com.example.tascade.ui.components.TodoTopBar
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground


@Composable
fun TodoApp() {
    val vm = TodoViewModel()
    Scaffold(
        topBar = { TodoTopBar() },
        bottomBar = { TodoBottomBar(
            onClearClicked = {vm.clearCompleted()},
            checkIfCompleted = {
            vm.todos.forEach { task->
                if(task.isCompleted){
                    return@TodoBottomBar true
                }
            }
                return@TodoBottomBar false
        })},
        floatingActionButton = { TodoFAB(onAddClicked = {incomingTitle:String -> vm.addTodo(titleInput = incomingTitle)}) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {

            TodoList(vm.todos, contentPaddingValues = innerPadding, onTaskChecked = { task -> vm.updateTask(task) })

        }
    }
}

@Composable
fun TodoList(
    tasks: List<Todo>,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues,
    onTaskChecked:(task:Todo)->Unit
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
                    onCheckedChange = { onTaskChecked(task) },
                    isCurrentlyChecked = task.isCompleted,
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


