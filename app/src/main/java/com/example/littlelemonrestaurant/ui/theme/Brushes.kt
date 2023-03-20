package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*

val brush_1 = Brush.horizontalGradient(
    listOf( Color(0xFFFFC107),
        LittleLemonColor.Yellow,
        LittleLemonColor.White,
        LittleLemonColor.Yellow))

val brush_2 = Brush.horizontalGradient(
    listOf( LittleLemonColor.Black,
        LittleLemonColor.Black,
        LittleLemonColor.Grey,
        LittleLemonColor.Black))


val largeRadialGradient = object : ShaderBrush()
{
    override fun createShader(size: Size): Shader
    {
        val biggerDimension = maxOf(size.height, size.width)

        return RadialGradientShader(

            colors = listOf(
                Color(0xFF2be4dc),
                Color(0xFF243484)
            ),

            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}//END largeRadialGradient