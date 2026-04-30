package com.example.tascade.ui.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.data.OfflineTodoRepository
import com.example.tascade.data.TodoDatabase
import com.example.tascade.model.Todo
import com.example.tascade.ui.components.TodoBottomBar
import com.example.tascade.ui.components.TodoCard
import com.example.tascade.ui.components.TodoFAB
import com.example.tascade.ui.components.TodoTopBar
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground
import kotlin.math.abs


@Composable
fun TodoApp() {
    val databaseObject = TodoDatabase.getDatabase(context = LocalContext.current)
    val vm = remember { TodoViewModel(repository = OfflineTodoRepository(todoDao = databaseObject.todoDao()))}
    val tasks by vm.todos.collectAsState()
    val showClearButton by vm.hasCompletedTasks.collectAsState(initial = false)

    Scaffold(
        topBar = { TodoTopBar() },
        bottomBar = { TodoBottomBar(onClearClicked = {vm.clearCompleted()}, showClearButton)},
        floatingActionButton = { TodoFAB(
            onAddClicked = {
                    incomingTitle:String ->
                vm.addTodo(titleInput = incomingTitle)
            }
        )
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFACC15))
                .halftoneBackground(),
            contentAlignment = Alignment.Center

        ) {
            val lazyListState = rememberLazyListState()

            TodoList(
                tasks = tasks,
                contentPaddingValues = PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 0.dp,
                    end = 0.dp
                ),
                onTaskChecked = { task -> vm.updateTask(task) },
                lazyListState = lazyListState
            )

        }
    }
}

@Composable
fun TodoList(
    tasks: List<Todo>,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues,
    onTaskChecked:(task:Todo)->Unit,
    lazyListState: LazyListState
) {

    //We only enable the IOS scroll effect when there are enough items to actually scroll.
    val enable3DEffect by remember(tasks) {
        derivedStateOf {
            tasks.size >= 5
        }
    }

    //animate the transition between normal list and 3D scroll
    val effectFactor by animateFloatAsState(
        targetValue = if (enable3DEffect) 1f else 0f,
        animationSpec = tween(durationMillis = 600),
        label = "3DEffectFactor"
    )

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
        LazyColumn(
            state = lazyListState,
            modifier = modifier.fillMaxSize(),
            contentPadding = contentPaddingValues,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items = tasks, key = { _, task -> task.id }) { index, task ->
                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem()
                ) {
                    TodoCard(
                        task = task,
                        onCheckedChange = { onTaskChecked(task) },
                        isCurrentlyChecked = task.isCompleted,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
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
