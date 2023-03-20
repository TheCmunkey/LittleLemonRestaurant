package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


object LittleLemonColor
{
    val Yellow = Color(0xFFFFEC14)  //0xFFFFEC14
    val Grey   = Color(0xFF495E57)
    val Black  = Color(0xFF000000)
    val White  = Color(0xFFFFFFFF)
    val Green  = Color(0xFF2B5326)
    //val Invisible= Color(0x00000000)
    val DarkGray = Color(0xFF404040)
    val DarkRed  = Color(0xFFB60202)
}

@Composable
fun get_textFieldColors(): TextFieldColors {

    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = LittleLemonColor.Yellow,
        unfocusedBorderColor = Color.DarkGray,
        backgroundColor = Color.LightGray,
        focusedLabelColor = LittleLemonColor.DarkGray,
        unfocusedLabelColor = LittleLemonColor.DarkGray,
        errorLabelColor = LittleLemonColor.DarkRed
    )

}
