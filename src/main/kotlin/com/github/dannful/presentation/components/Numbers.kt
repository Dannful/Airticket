package com.github.dannful.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.dannful.domain.InputState
import java.time.YearMonth
import java.util.*
import kotlin.math.roundToInt

private fun daysInMonth(month: Int) = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), month).lengthOfMonth()

@Composable
fun ColumnScope.Numbers(inputState: InputState) {
    var sliderValue by rememberSaveable { mutableStateOf(1) }
    val isStateValid = inputState.state in listOf(2U, 5U, 16U, 9U, 11U, 3U, 6U, 17U, 7U)
    LaunchedEffect(inputState.state) {
        sliderValue = when (inputState.state) {
            17U -> Calendar.getInstance().get(Calendar.YEAR)
            else -> 1
        }
    }
    Column(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.align(Alignment.CenterHorizontally)) {
        if (isStateValid)
            Text(
                text = sliderValue.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        when (inputState.state) {
            2U, 5U, 16U -> Slider(value = sliderValue.toFloat(), onValueChange = {
                sliderValue = it.roundToInt()
            }, valueRange = 1f..12f, modifier = Modifier.align(Alignment.CenterHorizontally))

            7U, 9U, 11U -> Slider(value = sliderValue.toFloat(), onValueChange = {
                sliderValue = it.roundToInt()
            }, valueRange = 0f..544f, modifier = Modifier.align(Alignment.CenterHorizontally))

            3U -> Slider(
                value = sliderValue.toFloat(),
                onValueChange = {
                    sliderValue = it.roundToInt()
                },
                valueRange = 1f..daysInMonth(inputState.answers[2U]!!.toInt()).toFloat(),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            6U -> Slider(
                value = sliderValue.toFloat(),
                onValueChange = {
                    sliderValue = it.roundToInt()
                },
                valueRange = 1f..daysInMonth(inputState.answers[5U]!!.toInt()).toFloat(),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            17U -> {
                val currentYear = Calendar.getInstance().get(Calendar.YEAR).toFloat()
                Slider(
                    value = sliderValue.toFloat(),
                    onValueChange = {
                        sliderValue = it.roundToInt()
                    },
                    valueRange = currentYear..(currentYear + 10),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        if (isStateValid)
            Button(onClick = {
                inputState.receiveInput(sliderValue.toString())
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Send")
            }
    }
}