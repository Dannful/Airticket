package com.github.dannful

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.github.dannful.presentation.InputScreen
import com.github.dannful.presentation.MainScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun ApplicationScope.app() {
    var currentWindow by rememberSaveable { mutableStateOf(0) }
    val fileFlow = remember { MutableSharedFlow<File>() }
    val scope = rememberCoroutineScope()
    val icon = painterResource("vin.png")
    Window(title = "Main menu", onCloseRequest = {
        exitApplication()
    }, icon = icon, visible = currentWindow == 0, state = WindowState(size = DpSize.Unspecified)) {
        MainScreen(window) {
            if (it != null) {
                scope.launch {
                    fileFlow.emit(it)
                }
            }
            currentWindow = 1
        }
    }
    Window(icon = icon, title = "Input menu", onCloseRequest = {
        exitApplication()
    }, visible = currentWindow == 1, state = WindowState(size = DpSize.Unspecified)) {
        InputScreen(fileFlow) {
            currentWindow = 0
        }
    }
}

fun main() {
    application {

        app()
    }
}