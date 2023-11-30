package com.example.stateflowexample

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val currentColor = savedStateHandle.getStateFlow("color", 0xFFFFFFFF)

    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        savedStateHandle["color"] = color
    }
}