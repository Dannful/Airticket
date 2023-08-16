package com.github.dannful.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.github.dannful.domain.InputState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputScreen() {
    val inputState = remember { InputState() }
    var text by rememberSaveable { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        LazyColumn {
            items(inputState.answers.keys.toList(), key = {
                it
            }) {
                val value = inputState.answers[it] ?: return@items
                Text(text = "q$it: $value")
            }
        }
        Text(text = "Estado: ${inputState.state}")
        TextField(value = text, singleLine = true, onValueChange = {
            text = it
        }, modifier = Modifier.onKeyEvent {
            if(it.key == Key.Enter) {
                inputState.receiveInput(text)
                text = ""
                return@onKeyEvent true
            }
            false
        })
    }
}