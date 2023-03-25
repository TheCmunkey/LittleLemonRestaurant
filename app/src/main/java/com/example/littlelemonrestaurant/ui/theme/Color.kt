package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color




object LittleLemonColor {
    val Yellow = Color(0xFFFFEC14)  //0xFFFFEC14
    val Grey = Color(0xFF495E57)
    val LightGrey = Color(0xFF5A7A70)
    val Black = Color(0xFF000000)
    val White = Color(0xFFFFFFFF)
    val Green = Color(0xFF2B5326)

    //val Invisible= Color(0x00000000)
    val DarkGray = Color(0xFF404040)
    val DarkRed = Color(0xFFB60202)
}

@Composable
fun get_textFieldColors(): TextFieldColors {

    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = LittleLemonColor.Yellow,
        unfocusedBorderColor = Color.DarkGray,
        backgroundColor = LittleLemonColor.LightGrey,
        focusedLabelColor = LittleLemonColor.DarkGray,
        unfocusedLabelColor = LittleLemonColor.DarkGray,
        errorLabelColor = LittleLemonColor.DarkRed,

    )

}

