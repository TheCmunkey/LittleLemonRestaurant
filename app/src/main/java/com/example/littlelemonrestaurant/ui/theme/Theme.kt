package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor.Black

private val DarkColorPalette = darkColors(
    primary = Black,
    primaryVariant = Black,
    secondary = LittleLemonColor.DarkGray
)

private val LightColorPalette = lightColors(
    primary = Black,
    primaryVariant = Black,
    secondary =  LittleLemonColor.DarkGray

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun LittleLemonRestaurantTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}


val DEFAULT_SCREEN_PADDING_HORIZONTAL = 20.dp
val DEFAULT_SCREEN_PADDING_VERTICAL = 20.dp