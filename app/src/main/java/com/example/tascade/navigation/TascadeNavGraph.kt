package com.example.tascade.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tascade.TodoViewModel
import com.example.tascade.data.OfflineTodoRepository
import com.example.tascade.data.TodoDatabase
import com.example.tascade.navigation.AppRoutes.POMODORO
import com.example.tascade.navigation.AppRoutes.TASKS
import com.example.tascade.ui.list.PomodoroScreen
import com.example.tascade.ui.list.TodoList
import com.example.tascade.ui.list.TodoScreen

@Composable
fun TascadeNavGraph(
    navController: NavHostController,
    modifier:Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = TASKS,
        //navigation graph is created in memory by this
        /*builder = {
            composable(AppRoutes.TASKS) { /* ... */ }
        }*/
    ){ // <-- The builder lambda was moved outside the parentheses
        composable(route = TASKS){
            //Database Instantiation
            val databaseObject = TodoDatabase.getDatabase(context = LocalContext.current)
            //ViewModel Instantiation
            val vm = remember { TodoViewModel(repository = OfflineTodoRepository(todoDao = databaseObject.todoDao()))}
            val tasks by vm.todos.collectAsState()
            TodoScreen(
                tasks = tasks,
                vm = vm
            )
        }
        composable(route = POMODORO){

        }
    }
}