package com.example.mytestapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ContactScreen(paddingModifier: Modifier) {
    Box(
        modifier = paddingModifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Text(
            text = "Contact",
            color = MaterialTheme.colorScheme.primary,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
@Preview(showBackground = true)
fun ContactScreenPreview(paddingModifier: Modifier) {
    ContactScreen(paddingModifier)
}