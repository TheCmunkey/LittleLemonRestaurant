package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun Modifier.textGradientBrush(brush: Brush) = this
    .graphicsLayer(alpha = 0.99f)
    .drawWithCache {
        onDrawWithContent {
            drawContent()
            drawRect(brush, blendMode = BlendMode.SrcAtop)
        }
    }

@Composable
fun DropShadowText(
    modifierTop: Modifier = Modifier,
    modifierBot: Modifier = Modifier,
    textcolor: Color = Color.Black,
    shadowcolor: Color = Color.Black,
    alpha: Float = 0.25f,
    offsetX: Dp = -(1.dp),
    offsetY: Dp = -(2.dp),
    radius: Dp = 2.dp,
    text: String = "",
    fontSize: TextUnit = 30.sp,
    fontWeight: Int = 500
) {
    Box()
    {

        Text(
            modifier = modifierBot
                .alpha(alpha)
                .offset(x = offsetX, y = offsetY)
                .blur(radius),
            color = shadowcolor,
            fontSize = fontSize,
            fontWeight = FontWeight(fontWeight),
            text = text
        )
        Text(
            modifier = modifierTop,
            color = textcolor,
            fontSize = fontSize,
            fontWeight = FontWeight(fontWeight),
            text = text
        )
    }
}


/**
 * px/dp/sp/pt/mm unit converter.
 * This is not incompatible with Jetpack Compose.
 */
//inline class Pixel(private val pxF: Float) {
//    val toPx: Int @Px get() = pxF.roundToInt()
//
//    fun toDp(res: Resources = Resources.getSystem()): Float = pxF / TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_DIP, 1.0f, res.displayMetrics
//    )
//
//    fun toSp(res: Resources = Resources.getSystem()): Float = pxF / TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_SP, 1.0f, res.displayMetrics
//    )
//
//    fun toPt(res: Resources = Resources.getSystem()): Float = pxF / TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_PT, 1.0f, res.displayMetrics
//    )
//
//    fun toMm(res: Resources = Resources.getSystem()): Float = pxF / TypedValue.applyDimension(
//        TypedValue.COMPLEX_UNIT_MM, 1.0f, res.displayMetrics
//    )
//
//    companion object {
//        fun fromDp(dp: Float, res: Resources = Resources.getSystem()) = Pixel(TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP, dp, res.displayMetrics
//        ))
//
//        fun fromSp(sp: Float, res: Resources = Resources.getSystem()) = Pixel(TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_SP, sp, res.displayMetrics
//        ))
//
//        fun fromPt(pt: Float, res: Resources = Resources.getSystem()) = Pixel(TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_PT, pt, res.displayMetrics
//        ))
//
//        fun fromMm(mm: Float, res: Resources = Resources.getSystem()) = Pixel(
//            TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_MM, mm, res.displayMetrics
//        ))
//    }
//}
//
//inline val Int.px get() = Pixel(pxF = this.toFloat())
//inline val Float.px get() = Pixel(pxF = this)
//
//inline val Int.dp get() = Pixel.fromDp(dp = this.toFloat())
//inline val Float.dp get() = Pixel.fromDp(dp = this)
//
//inline val Int.sp get() = Pixel.fromSp(sp = this.toFloat())
//inline val Float.sp get() = Pixel.fromSp(sp = this)
//
//inline val Int.pt get() = Pixel.fromPt(pt = this.toFloat())
//inline val Float.pt get() = Pixel.fromPt(pt = this)
//
//inline val Int.mm get() = Pixel.fromMm(mm = this.toFloat())
//inline val Float.mm get() = Pixel.fromMm(mm = this)

@Composable
fun ShadowBox(modifier: Modifier, brush: Brush) {
    Box(
        modifier = modifier
            .background(brush)
    )
}//END ShadowBox