package com.example.hotel.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Green500,
    onPrimary = Blake901,
    secondary = Blake800,
    onSecondary = Blake901,
    background = Blake900,
    error = Red400,
    secondaryVariant = Blake800
)

private val LightColorPalette = lightColors(
    primary = Green500,
    onPrimary = White,
    secondary = Green400,
    onSecondary = Gray100,
    background = White,
    error = Red400,
    secondaryVariant = Gray200
)

val Colors.textPrimaryColor: Color
    get() = if (isLight) Gray900 else White

val Colors.textSecondaryColor: Color
    get() = if (isLight) Gray700 else Gray100

val Colors.textThirdColor: Color
    get() = if (isLight) Gray700 else Gray400

//val Colors.textThirdColor: Color
//    get() = Gray500

val Colors.textForthColor: Color
    get() = if (isLight) Green500 else White

val Colors.textFifthColor: Color
    get() = White

val Colors.star: Color get() = Yellow500

@Composable
fun HotelTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = colors.background,
        darkIcons = !darkTheme
    )

    MaterialTheme(
        colors = colors,
        typography = UrbanistTypography,
        shapes = Shapes,
        content = content
    )
}