package com.github.dannful.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.dannful.util.CreditCard
import java.time.YearMonth
import java.util.*

class InputState {

    var state by mutableStateOf(0U)
        private set

    val answers = mutableStateMapOf<UInt, String>()

    private fun processPrevious(input: String, newState: UInt): Boolean {
        if (input == Alphabet.PREVIOUS) {
            state = newState
            return true
        }
        return false
    }

    private fun processMonth(input: String, newState: UInt): InputProcessResult {
        val month = input.toUIntOrNull() ?: return InputProcessResult.UNDEFINED_FUNCTION
        if (month in 1U..12U) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processText(input: String, newState: UInt): InputProcessResult {
        if (input.isNotBlank()) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processNumber(input: String, newState: UInt): InputProcessResult {
        if (input.toUIntOrNull() != null) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processState(
        input: String,
        previousState: UInt,
        newState: UInt,
        function: (String, UInt) -> InputProcessResult
    ): InputProcessResult {
        if (processPrevious(input, previousState))
            return InputProcessResult.INPUT_OK
        return function(input, newState)
    }

    private fun processState(
        input: String,
        previousState: UInt,
        newState: UInt,
        function: (String) -> InputProcessResult
    ): InputProcessResult {
        if (processPrevious(input, previousState))
            return InputProcessResult.INPUT_OK
        if (function(input) == InputProcessResult.INPUT_OK) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processState(
        input: String,
        newState: UInt,
        function: (String) -> InputProcessResult
    ): InputProcessResult {
        if (function(input) == InputProcessResult.INPUT_OK) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processDay(input: String, monthState: UInt, previousState: UInt, newState: UInt): InputProcessResult {
        if (processPrevious(input, previousState))
            return InputProcessResult.INPUT_OK
        return processDay(input, monthState, newState)
    }

    private fun processDay(input: String, monthState: UInt, newState: UInt): InputProcessResult {
        val upperBound =
            YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), answers[monthState]!!.toInt()).lengthOfMonth()
                .toUInt()
        if (input.toUIntOrNull() in 1U..upperBound) {
            answers[state] = input
            state = newState
            return InputProcessResult.INPUT_OK
        }
        return InputProcessResult.UNDEFINED_FUNCTION
    }

    private fun processTwoWay(input: String): InputProcessResult {
        if (processPrevious(input, 3U))
            return InputProcessResult.INPUT_OK
        val previousState = state
        state = when (input) {
            Alphabet.TWO_WAY -> 5U
            Alphabet.ONE_WAY -> 7U
            else -> return InputProcessResult.UNDEFINED_FUNCTION
        }
        answers[previousState] = input
        return InputProcessResult.INPUT_OK
    }

    private fun processMinors(input: String): InputProcessResult {
        val previousState = state
        state = when(input) {
            Alphabet.MINORS_ABOVE_TWO -> 12U
            Alphabet.MINORS_UNDER_TWO -> 11U
            else -> return InputProcessResult.UNDEFINED_FUNCTION
        }
        answers[previousState] = input
        return InputProcessResult.INPUT_OK
    }

    private fun processAdultsOrMinors(input: String): InputProcessResult {
        if(processPrevious(input, 7U))
            return InputProcessResult.INPUT_OK
        val previousState = state
        state = when(input) {
            Alphabet.ADULTS -> 12U
            Alphabet.MINORS -> 9U
            else -> return InputProcessResult.UNDEFINED_FUNCTION
        }
        answers[previousState] = input
        return InputProcessResult.INPUT_OK
    }

    fun receiveInput(input: String): InputProcessResult {
        return when (state) {
            0U -> processText(input, 1U)
            1U -> processState(input, 0U, 2U, ::processText)
            2U -> processState(input, 1U, 3U, ::processMonth)
            3U -> processDay(input, 2U, 2U, 4U)
            4U -> processTwoWay(input)
            5U -> processState(input, 4U, 6U, ::processMonth)
            6U -> processDay(input, 5U, 7U)
            7U -> processState(input, 4U, 8U, ::processNumber)
            8U -> processAdultsOrMinors(input)
            9U -> processState(input, 8U, 10U, ::processNumber)
            10U -> processMinors(input)
            11U -> processNumber(input, 12U)
            12U -> processState(input, 8U, 13U) { s ->
                return@processState if (s in listOf(
                        Alphabet.ECONOMY_CLASS,
                        Alphabet.EXECUTIVE_CLASS,
                        Alphabet.FIRST_CLASS
                    )
                ) InputProcessResult.INPUT_OK else InputProcessResult.UNDEFINED_FUNCTION
            }

            13U -> processState(input, 12U, 14U) { s ->
                return@processState if (s in listOf(
                        Alphabet.LATAM,
                        Alphabet.GOL,
                        Alphabet.AZUL
                    )
                ) InputProcessResult.INPUT_OK else InputProcessResult.UNDEFINED_FUNCTION
            }

            14U -> processState(input, 15U) { s ->
                return@processState if (s == Alphabet.FINISH) InputProcessResult.INPUT_OK else InputProcessResult.UNDEFINED_FUNCTION
            }

            15U -> processState(input, 0U, 16U) { s ->
                val creditNumber = s.toLongOrNull() ?: return@processState InputProcessResult.UNDEFINED_FUNCTION
                return@processState if (CreditCard.isValid(creditNumber)) InputProcessResult.INPUT_OK else InputProcessResult.UNDEFINED_FUNCTION
            }

            16U -> processState(input, 0U, 17U, ::processMonth)
            17U -> processState(input, 0U, 18U) { s ->
                val year = s.toIntOrNull() ?: return@processState InputProcessResult.UNDEFINED_FUNCTION
                return@processState if (year >= Calendar.getInstance()
                        .get(Calendar.YEAR)
                ) InputProcessResult.INPUT_OK else InputProcessResult.UNDEFINED_FUNCTION
            }

            18U -> processState(input, 0U, 19U, ::processNumber)
            19U -> processState(input, 0U, 20U, ::processText)
            20U -> InputProcessResult.FINAL_STATE
            else -> InputProcessResult.UNDEFINED_FUNCTION
        }
    }
}