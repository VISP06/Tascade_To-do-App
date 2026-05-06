package com.example.tascade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PomodoroViewModel(): ViewModel() {

    private val _timerValue = MutableStateFlow<Int>(_workDuration)
    val timerValue: StateFlow<Int> = _timerValue.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning:StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _isWorkSession = MutableStateFlow(true)
    val isWorkSession:StateFlow<Boolean> = _isWorkSession.asStateFlow()

    private val _workDuration = MutableStateFlow(1500)


    fun pauseTimer(){
        _isRunning.value = false
    }

    fun resetTimer(){
        _isRunning.value = false
        _timerValue.value = 1500
    }

    fun startTimer(){
        viewModelScope.launch {
            _isRunning.value = true
            while(_isRunning.value == true && _timerValue.value>0){
                delay(1000L)
                _timerValue.value -= 1
            }
        }
    }
}