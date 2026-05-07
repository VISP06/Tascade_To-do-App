package com.example.tascade

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PomodoroViewModel : ViewModel() {

    private val _timerValue = MutableStateFlow(1500)
    val timerValue: StateFlow<Int> = _timerValue.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _isWorkSession = MutableStateFlow(true)
    val isWorkSession: StateFlow<Boolean> = _isWorkSession.asStateFlow()

    private val _workDuration = MutableStateFlow(1500)
    val workDuration: StateFlow<Int> = _workDuration.asStateFlow()

    private val _breakDuration = MutableStateFlow(300)
    val breakDuration: StateFlow<Int> = _breakDuration.asStateFlow()

    fun pauseTimer() {
        _isRunning.value = false
    }

    fun resetTimer() {
        _isRunning.value = false
        if (_isWorkSession.value) {
            _timerValue.value = _workDuration.value
        } else {
            _timerValue.value = _breakDuration.value
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            _isRunning.value = true

            while (_isRunning.value && _timerValue.value > 0) {
                delay(1000L)
                _timerValue.value -= 1
            }
            if (_timerValue.value == 0) {
                _isWorkSession.value = !_isWorkSession.value
                resetTimer()
            }
        }
    }

    fun increaseWorkTime() {
        if (_workDuration.value <= 3000) {
            _workDuration.value += 60
        }
    }

    fun decreaseWorkTime() {
        if (_workDuration.value > 60) {
            _workDuration.value -= 60
        }
    }

    fun increaseBreakTime(){
        if(_breakDuration.value<=900){
            _breakDuration.value += 60
        }
    }

    fun decreaseBreakTime(){
        if(_breakDuration.value>300){
            _breakDuration.value -= 60
        }
    }
}