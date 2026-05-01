package com.example.tascade.data

import com.example.tascade.model.Todo
import kotlinx.coroutines.flow.Flow

//this file acts as a layer between the database and the interface whose methods will be actually used by the codebase
//mainly used for easy testing and we also create offline/online version incase the app decides to expand and use cloud database. you won't have to alter any code just create an online version (ig)
interface TodoRepository {

    fun getAllTodos(): Flow<List<Todo>>

    suspend fun insertTodo(task: Todo)

    suspend fun updateTodo(task: Todo)

    suspend fun deleteTodo(task: Todo)
}