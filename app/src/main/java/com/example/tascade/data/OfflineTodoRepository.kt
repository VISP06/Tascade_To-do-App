package com.example.tascade.data

import com.example.tascade.model.Todo
import kotlinx.coroutines.flow.Flow


class OfflineTodoRepository(private val todoDao: TodoDao): TodoRepository {

    override fun getAllItemsStream(): Flow<List<Todo>> =todoDao.getAllTodos()

    override suspend fun updateTodo(task: Todo) = todoDao.updateTodo(task)

    override suspend fun insertTodo(task: Todo) = todoDao.insertTodo(task)

    override suspend fun clearCompletedTodos() = todoDao.clearCompletedTodos()
}