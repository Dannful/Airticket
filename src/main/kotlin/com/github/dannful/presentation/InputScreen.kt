package com.github.dannful.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.dannful.domain.Alphabet
import com.github.dannful.domain.InputState
import com.github.dannful.presentation.components.*

@Composable
fun InputScreen() {
    val inputState = remember { InputState() }
    Column(
        modifier = Modifier.padding(16.dp).size(800.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PreviousButton(inputState)
        Text(
            text = "Estado atual: ${inputState.state}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Titles(inputState.state)
        Choices(inputState)
        Texts(inputState)
        Numbers(inputState)
    }
}