package com.example.samplemeapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = BackgroundPrimary,
    primaryContainer = PrimaryLightBlue,
    onPrimaryContainer = TextPrimary,
    secondary = SecondaryGreen,
    onSecondary = BackgroundPrimary,
    secondaryContainer = SecondaryLightGreen,
    onSecondaryContainer = TextPrimary,
    tertiary = PrimaryLightBlue,
    onTertiary = BackgroundPrimary,
    background = BackgroundSecondary,
    onBackground = TextPrimary,
    surface = BackgroundPrimary,
    onSurface = TextPrimary,
    surfaceVariant = BackgroundSecondary,
    onSurfaceVariant = TextSecondary,
    error = StatusColors.Error,
    onError = BackgroundPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = BackgroundPrimary,
    primaryContainer = PrimaryLightBlue,
    onPrimaryContainer = TextPrimary,
    secondary = SecondaryGreen,
    onSecondary = BackgroundPrimary,
    secondaryContainer = SecondaryLightGreen,
    onSecondaryContainer = TextPrimary,
    tertiary = PrimaryLightBlue,
    onTertiary = BackgroundPrimary,
    background = BackgroundPrimary,
    onBackground = TextPrimary,
    surface = BackgroundPrimary,
    onSurface = TextPrimary,
    surfaceVariant = BackgroundSecondary,
    onSurfaceVariant = TextSecondary,
    error = StatusColors.Error,
    onError = BackgroundPrimary
)

@Composable
fun SampleMeApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = if (darkTheme) BackgroundSecondary else BackgroundPrimary
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}