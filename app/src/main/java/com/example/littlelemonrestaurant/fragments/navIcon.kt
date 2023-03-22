package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AdvancedIcon(
    icon_id: Int,
    text: String, textColor:
                 Color = Color(0xFF000000),
    navController: NavHostController)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    )
    {

        if(icon_id !=0) {
            Icon(
                painter = painterResource(icon_id),
                contentDescription = null,
                 tint = Color.DarkGray,
                // tint  = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
                modifier = Modifier.size(48.dp)
                    .clickable {
                        if(text=="Home") navController.navigate(HomeScreen.route)
                        else  navController.navigate(ProfileScreen.route)
                    }
            )

        }
        // gap between icon and text

        if (text.isNotEmpty()) {

            Spacer(modifier = Modifier.width(width = 6.dp))

//            Text(
//                text = text,
//                color = textColor,
//                fontSize = 20.sp
//            )

        }

        Spacer(modifier = Modifier.width(width = 16.dp))

    }//END Row()
}
