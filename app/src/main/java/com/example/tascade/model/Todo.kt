package com.example.tascade.model

import androidx.annotation.StringRes

data class Todo(
    val id:Int,
    val title:String,
    val desc:String,
    val priority:Int,
    val isCompleted:Boolean = false
)

//add priority, checkbox, sound effects, dropdown for more context, progress bar, ios feel to scroll and neo-brutalist aesthetic to components