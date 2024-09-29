package com.example.act22.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.act22.R

// Dark and Light Color Schemes
private val DarkColorScheme = darkColorScheme(
    primary = NewDark,
    secondary = NewWhite,
    tertiary = NewPurple,
    background = NewDark,
    onSecondary = SupportWhite,
    onTertiary = SupportBlue
)

private val LightColorScheme = lightColorScheme(
    primary = NewWhite,
    secondary = NewDark,
    tertiary = NewPurple,
    background = NewWhite,
    onSecondary = SupportWhite,
    onTertiary = SupportBlue
)

@Composable
fun ACT22Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun getLogoResource(darkTheme: Boolean = isSystemInDarkTheme()): Int {
    return if (darkTheme) {
        R.drawable.logo_dark // Your dark theme logo resource
    } else {
        R.drawable.logo_bright // Your light theme logo resource
    }
}

