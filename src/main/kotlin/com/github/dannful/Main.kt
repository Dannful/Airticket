package com.github.dannful

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.singleWindowApplication
import com.github.dannful.presentation.InputScreen
import com.github.dannful.presentation.MainScreen

@Composable
fun App() {
    var currentWindow by rememberSaveable { mutableStateOf(0) }
    MaterialTheme {
        when (currentWindow) {
            0 -> MainScreen {
                currentWindow = 1
            }
            1 -> InputScreen()
        }
    }
}

fun main() = singleWindowApplication {
    App()
}