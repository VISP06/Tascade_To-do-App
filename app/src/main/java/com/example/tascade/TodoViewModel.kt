package com.example.tascade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tascade.data.TodoRepository
import com.example.tascade.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TodoViewModel(private val repository: TodoRepository): ViewModel() {

    //we need the list returned to us from the database in Stateflow<todo> format and not in Flow<Todo>
    val todos: StateFlow<List<Todo>> = repository.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    //the database work must run in the background and we achieve this using coroutines, the actual insertion is handled by a background worker
    fun addTodo(titleInput:String){
        val newTask = Todo(
            title = titleInput,
            isCompleted = false
        )
        viewModelScope.launch{
            repository.insertTodo(newTask)
        }
    }

    fun deleteTodo(task: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(task)
        }
    }

    fun updateTask(task:Todo){
        val clonedTask = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch {
            repository.updateTodo(clonedTask)
        }
    }
}