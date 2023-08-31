package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.dannful.domain.InputState
import java.awt.Dimension

@Composable
fun ColumnScope.Answers(inputState: InputState) {
    val composeDialog = remember {
        ComposeDialog().apply {
            size = Dimension(400, 400)
            isVisible = false
            title = "Previous answers"
        }
    }
    Button(onClick = {
        composeDialog.isVisible = !composeDialog.isVisible
    }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
        Text(text = "Ver respostas")
    }
    Dialog(visible = composeDialog.isVisible, dispose = ComposeDialog::dispose, create = {
        composeDialog
    }) {
        LazyColumn(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(inputState.answers.keys.toList(), key = {
                it
            }) {
                val value = inputState.answers[it] ?: return@items
                Text(text = "Estado $it: $value")
            }
        }
    }
}