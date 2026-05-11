package com.example.tascade.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tascade.PomodoroViewModel
import com.example.tascade.navigation.TascadeNavGraph
import com.example.tascade.ui.components.MainBottomBar
import com.example.tascade.ui.theme.TascadeTheme

@Composable
fun TascadeApp() {
    //Navigation Instantiation
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {MainBottomBar(navController = navController)}
    ) {
        innerPadding->
        val pvm: PomodoroViewModel = viewModel()
        TascadeNavGraph(navController = navController, innerPadding = innerPadding, pomodoroViewModel = pvm)
    }
}

@Preview(showBackground = true)
@Composable
fun TascadeAppPreview() {
    TascadeTheme {
        TascadeApp()
    }
}