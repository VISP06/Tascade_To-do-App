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
            val screenHeight = (LocalConfiguration.current.screenHeightDp.dp)/5
            TodoList(
                tasks = tasks,
                contentPaddingValues = PaddingValues(
                    top = innerPadding.calculateTopPadding() + screenHeight,
                    bottom = innerPadding.calculateBottomPadding() + screenHeight,
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
                        .graphicsLayer {
                            val layoutInfo = lazyListState.layoutInfo
                            val todoInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }

                            if (todoInfo != null && effectFactor > 0f) {
                                // Calculate the center of the viewport
                                val viewportHeight = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
                                val viewportCenter = viewportHeight / 2f

                                //Calculate item's center position relative to viewport
                                val itemCenter = todoInfo.offset + todoInfo.size / 2f

                                //Normalized distance from center (-1.0 to 1.0)
                                val distanceFromCenter = (itemCenter - viewportCenter) / viewportCenter
                                
                                //Rotation: Items tilt away as they move towards the edges
                                //Applying effectFactor for smooth transition
                                rotationX = (-35f * distanceFromCenter) * effectFactor

                                //Perspective Depth: Adjust camera to make the 3D effect pop
                                cameraDistance = 8f * density

                                //Translation: Subtle Y-offset to mimic the curvature of a cylinder.
                                translationY = (distanceFromCenter * (todoInfo.size / 4f)) * effectFactor

                                //Scale: Items get slightly smaller as they rotate away
                                val targetScale = 1f - (abs(distanceFromCenter) * 0.12f)
                                val currentScale = 1f + (targetScale - 1f) * effectFactor
                                scaleX = currentScale.coerceIn(0.88f, 1f)
                                scaleY = currentScale.coerceIn(0.88f, 1f)

                                //Subtle fade for items at the far edges for more realism
                                val targetAlpha = (1f - abs(distanceFromCenter) * 0.3f).coerceIn(0.6f, 1f)
                                alpha = 1f + (targetAlpha - 1f) * effectFactor
                                
                                // Ensure rotation happens around the center
                                transformOrigin = TransformOrigin(0.5f, 0.5f)
                            }
                        }
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
