package com.example.tascade

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tascade.model.Todo

class TodoViewModel: ViewModel() {
    private val _todos = mutableStateListOf<Todo>()

    val todos: List<Todo> = _todos
    fun addTodo(){
        //hardcoded task for testing
        val testTodo = Todo(
            id = _todos.size + 1,
            title = R.string.test_title,
            desc = "",
            priority = 1,
            isCompleted = false
        )
        _todos.add(0, testTodo)
    }
}
