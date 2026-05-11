package com.example.tascade.util

//helper method to convert raw time (eg. 1500) into proper formatted strings (eg. 25:00)
fun formatTimeString(time:Int):String{
    val timeInMinutes = time / 60
    val timeInSeconds = time % 60
    return String.format("%02d : %02d", timeInMinutes, timeInSeconds)
}