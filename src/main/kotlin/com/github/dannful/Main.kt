package com.github.dannful

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.github.dannful.presentation.InputScreen
import com.github.dannful.presentation.MainScreen
import java.io.File

@Composable
fun App(window: ComposeWindow) {
    var currentWindow by rememberSaveable { mutableStateOf(0) }
    var loadedFile by remember { mutableStateOf<File?>(null) }
    MaterialTheme {
        when (currentWindow) {
            0 -> MainScreen(window) {
                loadedFile = it
                currentWindow = 1
            }

            1 -> InputScreen(loadedFile) {
                currentWindow = 0
            }
        }
    }
}

fun main() {
    singleWindowApplication(state = WindowState(size = DpSize(100.dp, 100.dp))) {
        App(window)
    }
}