package com.example.tascade

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tascade.model.Todo

class TodoViewModel: ViewModel() {
    //this is the main list and any changes made to the list now causes recomposition
    private val _todos = mutableStateListOf<Todo>() //backing property
    private var count:Int = 0

    val todos: List<Todo> = _todos //public property
    fun addTodo(titleInput:String){
        val testTodo = Todo(
            id = count,
            title = titleInput,
            priority = 1,
            isCompleted = false
        )
        _todos.add(0, testTodo)
        count++
    }

    fun removeTodo(indexToRemove:Int): Unit{
        _todos.removeAt(indexToRemove)
    }
}
