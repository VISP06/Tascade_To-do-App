package com.example.tascade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tascade.ui.list.TodoApp
import com.example.tascade.ui.theme.TascadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TascadeTheme {
                TodoApp()
            }
        }
    }
}

