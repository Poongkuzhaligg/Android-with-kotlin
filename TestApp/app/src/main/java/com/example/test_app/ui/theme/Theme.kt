package com.example.test_app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.Providers

private val DarkThemeColors = mapOf(
    "BrandPrimary" to Color(0xFF1E88E5),
    "BrandDisabledButton" to Color(0xFF6AB7FF),
    "BrandTextLoading" to Color(0xFF2979FF),
    "AppBackground" to Color(0xFF121212),
    "AppSurface" to Color(0xFF1E1E1E),
    "TextPrimary" to Color(0xFFE0E0E0),
    "TextSecondary" to Color(0xFFB0BEC5),
    "TextDisabled" to Color(0xFF757575),
    "OtherError" to Color(0xFFCF6679),
    "GraphDataLine" to Color(0xFF90CAF9)
)

private val LightThemeColors = mapOf(
    "BrandPrimary" to Color(0xFF1565C0),
    "BrandDisabledButton" to Color(0xFF90CAF9),
    "BrandTextLoading" to Color(0xFF42A5F5),
    "AppBackground" to Color(0xFFFFFFFF),
    "AppSurface" to Color(0xFFFFFFFF),
    "TextPrimary" to Color(0xFF000000),
    "TextSecondary" to Color(0xFF757575),
    "TextDisabled" to Color(0xFFBDBDBD),
    "OtherError" to Color(0xFFD32F2F),
    "GraphDataLine" to Color(0xFF1E88E5)
)

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkThemeColors else LightThemeColors

    // Provide the custom colors to the content
    Providers(LocalCustomColors provides colors) {
        content()
    }
}

val LocalCustomColors = compositionLocalOf { LightThemeColors }