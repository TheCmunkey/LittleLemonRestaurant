package com.example.littlelemonrestaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController)
{
    val configuration = LocalConfiguration.current

    Column()
    {
        Text(
            text = "PROFILE",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(start = 0.dp, end = 0.dp)
        )
    }//END Column

}//END HomeScreen