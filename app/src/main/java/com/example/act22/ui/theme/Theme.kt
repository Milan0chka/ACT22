package com.example.act22.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.act22.R

// Dark and Light Color Schemes
private val LightColorScheme = lightColorScheme(
    primary = NewDarkPurple,
    secondary = NewPurple,
    tertiary = Lilac,
    background = NewWhite,
    surface = LilacGrey,
    onPrimary = NewWhite,
    onSecondary = NewWhite,
    onTertiary = NewDarkPurple,
    onBackground = NewDarkPurple,
    onSurface = PurpleGrey,
    secondaryContainer = NewWhite,
    onSecondaryContainer = NewDarkPurple
)

private val DarkColorScheme = darkColorScheme(
    primary = NewDarkPurple,
    secondary = NewPurple,
    tertiary = Lilac,
    background = NewBlack,
    surface = LilacGrey,
    onPrimary = NewWhite,
    onSecondary = NewWhite,
    onTertiary = NewDarkPurple,
    onBackground = NewDarkPurple,
    onSurface = PurpleGrey,
    secondaryContainer = PurpleGrey,
    onSecondaryContainer = NewWhite
)

@Composable
fun ACT22Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
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
//    return if (darkTheme) {
//        R.drawable.logo_dark
//    } else {
//        R.drawable.logo_bright
//    }
    return  R.drawable.logo_bright
}

