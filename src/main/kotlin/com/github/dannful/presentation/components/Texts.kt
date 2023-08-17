package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import com.github.dannful.domain.InputState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ColumnScope.Texts(inputState: InputState) {
    var text by rememberSaveable { mutableStateOf("") }
    when (inputState.state) {
        0U, 1U, 15U, 18U, 19U ->
            TextField(value = text, singleLine = true, onValueChange = {
                text = it
            }, modifier = Modifier.align(Alignment.CenterHorizontally).onKeyEvent {
                if (it.key == Key.Enter) {
                    inputState.receiveInput(text)
                    text = ""
                    return@onKeyEvent true
                }
                false
            })
    }
}