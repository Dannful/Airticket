package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ButtonData(
    val displayText: String,
    val onClick: () -> Unit
)

@Composable
fun ColumnScope.ButtonRow(vararg buttonData: ButtonData) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(horizontal = 16.dp).align(
            Alignment.CenterHorizontally
        )
    ) {
        buttonData.forEach {
            Button(onClick = it.onClick) {
                Text(text = it.displayText)
            }
        }
    }
}