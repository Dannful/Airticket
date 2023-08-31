package com.github.dannful.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.dannful.domain.InputState
import com.github.dannful.presentation.components.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.io.File


fun processLoadedFile(inputState: InputState, loadedFile: File?) {
    if (loadedFile != null) {
        val inputs = loadedFile.readLines()[1].split(", ")
        inputs.forEach {
            inputState.receiveInput(it.trim())
        }
    }
}

@Composable
fun InputScreen(fileFlow: SharedFlow<File>, onReturn: () -> Unit) {
    val inputState = remember { InputState() }
    LaunchedEffect(Unit) {
        fileFlow.collectLatest {
            inputState.reset()
            processLoadedFile(inputState, it)
        }
    }
    Column(
        modifier = Modifier.padding(16.dp).size(800.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PreviousButton(inputState, onReturn)
        Text(
            text = "Estado atual: ${inputState.state}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Answers(inputState)
        Titles(inputState.state)
        Choices(inputState)
        Texts(inputState)
        Numbers(inputState)
    }
}