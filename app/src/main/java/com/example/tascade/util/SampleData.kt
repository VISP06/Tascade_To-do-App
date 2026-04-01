package com.example.tascade.util

import com.example.tascade.model.Todo

val sampleTasks = listOf<Todo>(
    Todo(id = 1, title = "Buy Milk", "Go to the supermarket and find shop named DairyDream Store",1, isCompleted = false),
    Todo(id = 2, title = "Finish fixing bug", "The dropdown button is not working as expected",2, isCompleted = true),
    Todo(id = 3, title = "Gym at 6 PM", "It is not leg day:)", 3, isCompleted = false)
)