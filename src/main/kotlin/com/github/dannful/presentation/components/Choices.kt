package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import com.github.dannful.domain.Alphabet
import com.github.dannful.domain.InputState

@Composable
fun ColumnScope.Choices(inputState: InputState) {
    when (inputState.state) {
        4U -> ButtonRow(ButtonData(displayText = "Ida e volta") {
            inputState.receiveInput(Alphabet.TWO_WAY)
        }, ButtonData(displayText = "Apenas ida") {
            inputState.receiveInput(Alphabet.ONE_WAY)
        })

        8U -> ButtonRow(ButtonData(displayText = "Passagens incluem menores de idade") {
            inputState.receiveInput(Alphabet.MINORS)
        }, ButtonData(displayText = "Passagens incluem somente adultos") {
            inputState.receiveInput(Alphabet.ADULTS)
        })

        10U -> ButtonRow(ButtonData(displayText = "Há menores com 2 anos de idade ou menos") {
            inputState.receiveInput(Alphabet.MINORS_UNDER_TWO)
        }, ButtonData(displayText = "Há somente menores com mais de 2 anos de idade") {
            inputState.receiveInput(Alphabet.MINORS_ABOVE_TWO)
        })

        12U -> ButtonRow(ButtonData(displayText = "Econômico") {
            inputState.receiveInput(Alphabet.ECONOMY_CLASS)
        }, ButtonData(displayText = "Executivo") {
            inputState.receiveInput(Alphabet.EXECUTIVE_CLASS)
        }, ButtonData(displayText = "Primeira classe") {
            inputState.receiveInput(Alphabet.FIRST_CLASS)
        })

        13U -> ButtonRow(ButtonData(displayText = "LATAM") {
            inputState.receiveInput(Alphabet.LATAM)
        }, ButtonData(displayText = "GOL") {
            inputState.receiveInput(Alphabet.GOL)
        }, ButtonData(displayText = "AZUL") {
            inputState.receiveInput(Alphabet.AZUL)
        })

        14U -> Button(onClick = {
            inputState.receiveInput(Alphabet.FINISH)
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Finish")
        }
        20U -> Button(onClick = {
            inputState.reset()
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Recomeçar")
        }
    }
}