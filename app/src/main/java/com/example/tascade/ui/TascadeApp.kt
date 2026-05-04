package com.example.tascade.ui

import android.media.SoundPool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tascade.R
import com.example.tascade.TodoViewModel
import com.example.tascade.data.OfflineTodoRepository
import com.example.tascade.data.TodoDatabase
import com.example.tascade.ui.components.TodoFAB
import com.example.tascade.ui.components.TodoTopBar
import com.example.tascade.ui.list.TodoList
import com.example.tascade.ui.theme.TascadeTheme
import com.example.tascade.util.halftoneBackground

@Composable
fun TascadeApp() {
    //Navigation Instantiation
    val navController = rememberNavController()
    //Database Instantiation
    val databaseObject = TodoDatabase.getDatabase(context = LocalContext.current)
    //ViewModel Instantiation
    val vm = remember { TodoViewModel(repository = OfflineTodoRepository(todoDao = databaseObject.todoDao()))}
    val tasks by vm.todos.collectAsState()

    //Sound effects for clicking
    val context = LocalContext.current

    val soundPool = remember {
        SoundPool.Builder()
            .setMaxStreams(5)
            .build()
    }

    val buttonSoundId = remember {
        soundPool.load(context, R.raw.button_press, 1)
    }

    Scaffold(
        topBar = { TodoTopBar(topBarHeadingId = R.string.top_bar_title1) },
        floatingActionButton = { TodoFAB(
            onAddClicked = {
                    incomingTitle:String ->
                vm.addTodo(titleInput = incomingTitle)
            },
            soundPool = soundPool,
            buttonSoundId = buttonSoundId,
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
                onTaskDeleted = { task -> vm.deleteTodo(task) },
                lazyListState = lazyListState,

                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TascadeAppPreview() {
    TascadeTheme {
        TascadeApp()
    }
}