package com.example.littlelemonrestaurant.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


val textBoxModifier = Modifier
.height(58.dp)
.fillMaxWidth(1.0f)
.border(
    BorderStroke(1.dp, color = Color(0x1F000000)),
    RoundedCornerShape(100)
)
.shadow(
    elevation = 20.dp,
    shape = RoundedCornerShape(100.dp),
    clip = false,
    ambientColor = LittleLemonColor.Black,
    spotColor = LittleLemonColor.Black
)
.shadow(
    elevation = 4.dp,
    shape = RoundedCornerShape(100.dp),
    clip = false,
    ambientColor = LittleLemonColor.Black,
    spotColor = LittleLemonColor.Black
)
.padding(horizontal = 1.dp, vertical = 0.dp)