package com.example.tascade.util

import com.example.tascade.R
import com.example.tascade.model.Todo

val sampleTasks = listOf<Todo>(
    Todo(id = 1, title = R.string.title_1, "Go to the supermarket and find shop named DairyDream Store",1, isCompleted = false),
    Todo(id = 2, title = R.string.title_2, "The dropdown button is not working as expected",2, isCompleted = true),
    Todo(id = 3, title = R.string.title_3, "It is not leg day:)", 3, isCompleted = false)
)