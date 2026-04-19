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
    /*this is the main list and any changes made to the list now causes recomposition
    private val _todos = mutableStateListOf<Todo>() //backing property
    private var count:Int = 0 //used to determine the id/index of a specific card

    var todos: List<Todo> = _todos //public property */

    //we need the list returned to us from the database in Stateflow<todo> format and not in Flow<Todo>
    val todos: StateFlow<List<Todo>> = repository.getAllItemsStream()
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

    val hasCompletedTasks: Flow<Boolean> = todos.map { list ->
        list.any { it.isCompleted }
    }

    fun clearCompleted(){
        viewModelScope.launch {
            repository.clearCompletedTodos()
        }
    }

    fun updateTask(task:Todo){
        val clonedTask = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch {
            repository.updateTodo(clonedTask)
        }
    }

    /*
    fun updateTask(task:Todo){
        val index = todos.indexOf(task) //to store the new cloned task we need the index of the original first
        val myClonedTask = task.copy(isCompleted = !task.isCompleted) //creation of clone with changes made to data
        _todos[index] = myClonedTask //overwriting the task stored at index with clone
        _todos.sortBy{it.isCompleted} //completed tasks get sorted to the bottom
    }*/
    //here we clone the task into a new variable with only a small change in its properties
    //we do this instead of just task.isCompleted = true  because Compose wouldn't reconstruct the screen with the updated data in case of the latter
    //whereas in the former, we basically overwrite the task with another just like it but with the changes made to its properties and since we are technically adding
    //a new task, compose reconstructs the screen
}
