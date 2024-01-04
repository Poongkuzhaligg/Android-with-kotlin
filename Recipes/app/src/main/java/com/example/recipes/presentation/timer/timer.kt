package com.example.recipes.presentation.timer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

//TODO: Show countdown timer for prep time and cook time
@Composable
fun TimerScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Timer SCREEN",
            fontSize = MaterialTheme.typography.title2.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Composable
@Preview
fun TimerPreview() {
    TimerScreen()
}