package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val brush_1 = Brush.horizontalGradient(
    listOf( Color(0xFFFFC107),
        LittleLemonColor.Yellow,
        LittleLemonColor.White,
        LittleLemonColor.Yellow))


val leftShadowBrush = Brush.horizontalGradient(

        0.0f to Color(0xaF000000),
        0.2f to Color(0x6F004040),
        0.7f to Color(0x1F004040),
        1.0f to Color(0x00004040),
)
val rightShadowBrush = Brush.horizontalGradient(

    0.0f to Color( 0x00004040),
    0.3f to Color( 0x1F004040),
    0.8f to Color( 0x6F004040),
    1.0f to Color( 0xaF000000),
)
val bottomShadowBrush = Brush.verticalGradient(

    0.0f to Color( 0x00000000),
    0.3f to Color( 0x1F000000),
    0.9f to Color( 0x3F000000),
    1.0f to Color( 0xaF000000),
)

//val largeRadialGradient = object : ShaderBrush()
//{
//    override fun createShader(size: Size): Shader
//    {
//        val biggerDimension = maxOf(size.height, size.width)
//
//        return RadialGradientShader(
//
//            colors = listOf(
//                Color(0xFF2be4dc),
//                Color(0xFF243484)
//            ),
//
//            center = size.center,
//            radius = biggerDimension / 2f,
//            colorStops = listOf(0f, 0.95f)
//        )
//    }
//}//END largeRadialGradient