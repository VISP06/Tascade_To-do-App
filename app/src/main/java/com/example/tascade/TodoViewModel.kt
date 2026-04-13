package com.example.tascade

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.tascade.model.Todo


class TodoViewModel: ViewModel() {
    //this is the main list and any changes made to the list now causes recomposition
    private val _todos = mutableStateListOf<Todo>() //backing property
    private var count:Int = 0 //used to determine the id/index of a specific card

    var todos: List<Todo> = _todos //public property
    fun addTodo(titleInput:String){
        val testTodo = Todo(
            id = count,
            title = titleInput,
            isCompleted = false
        )
        _todos.add(0, testTodo)
        count++
    }

    fun clearCompleted(){
        _todos.removeAll { it.isCompleted }
    }

    fun updateTask(task:Todo){
        val index = todos.indexOf(task) //to store the new cloned task we need the index of the original first
        val myClonedTask = task.copy(isCompleted = !task.isCompleted) //creation of clone with changes made to data
        _todos[index] = myClonedTask //overwriting the task stored at index with clone
        _todos.sortBy{it.isCompleted} //completed tasks get sorted to the bottom
    }
    //here we clone the task into a new variable with only a small change in its properties
    //we do this instead of just task.isCompleted = true  because Compose wouldn't reconstruct the screen with the updated data in case of the latter
    //whereas in the former, we basically overwrite the task with another just like it but with the changes made to its properties and since we are technically adding
    //a new task, compose reconstructs the screen
}
