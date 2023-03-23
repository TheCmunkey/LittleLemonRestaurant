package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.R

@Composable
fun TopNavBar(navController: NavHostController, currentScreen: String) {

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    )
    {
        Logo()
        Spacer(Modifier.weight(1f))
        AdvancedIcon(
            icon_id = R.drawable.baseline_home_24,
            text = currentScreen,
            navController = navController
        )
    }

}//END TopNavBar