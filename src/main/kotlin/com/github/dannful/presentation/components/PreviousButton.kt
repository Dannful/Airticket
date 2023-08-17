package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.dannful.domain.Alphabet
import com.github.dannful.domain.InputState

@Composable
fun ColumnScope.PreviousButton(inputState: InputState) {
    Box(modifier = Modifier.height(80.dp).align(Alignment.Start)) {
        when (inputState.state) {
            1U, 2U, 3U, 4U, 5U, 6U, 7U, 8U, 9U, 12U, 13U, 14U, 15U, 16U, 17U, 18U, 19U -> IconButton(
                onClick = {
                    inputState.receiveInput(Alphabet.PREVIOUS)
                }
            ) {
                Icon(
                    imageVector = if (inputState.state >= 15U) Icons.Default.Close else Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}