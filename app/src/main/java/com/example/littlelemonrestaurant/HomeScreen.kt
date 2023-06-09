package com.example.littlelemonrestaurant

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.fragments.DrawMenuItemsList
import com.example.littlelemonrestaurant.fragments.HeroPanel
import com.example.littlelemonrestaurant.fragments.MenuItemRoom
import com.example.littlelemonrestaurant.fragments.TopNavBar

var savedListState = LazyListState(0, 0)

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController,databaseMenuItems: List<MenuItemRoom>)
{

    val configuration = LocalConfiguration.current

    Column()
    {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            TopNavBar(navController, "ProfileScreen")
            HeroPanel()
            DrawMenuItemsList(databaseMenuItems)

        } else {
            TopNavBar(navController, "ProfileScreen")
            Row(Modifier.fillMaxHeight(1.0f))
            {
                Column(modifier = Modifier.fillMaxWidth(0.5f))
                {
                    HeroPanel()

                }//END COL
                Column(modifier = Modifier.fillMaxWidth(1.0f))
                {
                    DrawMenuItemsList(databaseMenuItems)
                }//END COL
            }//END Row
        }//END LandScape mode

    }//END Column

}//END HomeScreen




