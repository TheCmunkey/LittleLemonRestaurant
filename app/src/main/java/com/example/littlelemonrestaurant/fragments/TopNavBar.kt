package com.example.littlelemonrestaurant.fragments

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.DEFAULT_SCREEN_PADDING_HORIZONTAL
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.bottomShadowBrush

@Composable
fun TopNavBar(navController: NavHostController, currentScreen: String) {


    Box(modifier = Modifier.fillMaxWidth(1.0f))
    {
        Divider(Modifier
            .align(Alignment.TopCenter)
            .fillMaxWidth(1.0f),
            color=(LittleLemonColor.Grey),
            thickness = 1.dp,
            startIndent = 0.dp)
        ShadowBox(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(1.0f)
                .height(30.dp),
            bottomShadowBrush
        )
        Divider(Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(1.0f),
                color=(Color(0xaF000000)),
                thickness = 1.dp,
                startIndent = 0.dp)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .height(44.dp)
                .padding(horizontal = DEFAULT_SCREEN_PADDING_HORIZONTAL)
            //.background(Color.White)
        )
        {
            MenuIcon(navController = navController)
            Logo()
            NavIcon(navController = navController, currentScreen)
        }
    }
}//END TopNavBar

@Composable
fun MenuIcon(navController: NavHostController) {

    //val destination = Screens[destinationScreen]

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var iconColor =  LittleLemonColor.DarkGray
    if(isPressed)  iconColor =  LittleLemonColor.Yellow


    Column(horizontalAlignment = Alignment.Start,
           modifier = Modifier.fillMaxHeight(1.0f).width(42.dp).padding(0.dp))
    {

        IconButton(
            interactionSource = interactionSource,
            enabled = true,
            modifier = Modifier.fillMaxHeight(1.0f ),
            onClick = {
                //navController.navigate(destination.route)
            }
        )
        {
            Icon(painter = painterResource(R.drawable.menu_icon),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(0.7f),
                tint = iconColor)

        }//END IconButton()

    }//END Row()
}//END MenuIcon

@Composable
fun NavIcon(navController: NavHostController, destinationScreen: String) {

    val destination = Screens[destinationScreen]

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var iconColor =  LittleLemonColor.DarkGray
    if(isPressed)  iconColor =  LittleLemonColor.Yellow

    if (destination == null) { Log.e(null, "NavIcon: No Destination" ); return }

    Column( horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxHeight(1.0f).width(42.dp))
    {

        if (destination.iconId != 0) {
            IconButton(
                interactionSource = interactionSource,
                enabled = true,
                modifier = Modifier.fillMaxHeight(1.0f),
                onClick = {
                    navController.navigate(destination.route)
                }
            )
            {

                Icon(painter = painterResource(destination.iconId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight(1.9f).fillMaxWidth(),
                    tint = iconColor)
            }
        }

    }//END Row()
}//END NavIcon

@Composable
fun Logo() {

    Column( horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier.fillMaxHeight(1.0f))
    {
        Image(
            painter = painterResource(id = R.drawable.logo_masked),
            contentDescription = stringResource(id = R.string.logo),
            ///contentScale = ContentScale.Inside,
            modifier = Modifier
               // .fillMaxWidth(0.275f)
                .fillMaxHeight(1.0f)
                .background(Color.Transparent)
        )//END Image
    }//END Row
}