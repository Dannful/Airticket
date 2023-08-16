package com.github.dannful.core

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

val LocalWindow = compositionLocalOf {
    mutableStateOf(0)
}