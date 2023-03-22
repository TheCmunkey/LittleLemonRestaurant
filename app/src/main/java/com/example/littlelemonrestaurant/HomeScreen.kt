package com.example.littlelemonrestaurant

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.fragments.*


var savedListState = LazyListState(0,0)



@Composable
fun HomeScreen(navController: NavHostController)
{

    val configuration = LocalConfiguration.current
    val interactionSource = remember { MutableInteractionSource() }

    val contentScale = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        ContentScale.FillWidth
    }
    else {
        ContentScale.FillHeight
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.shadow(12.dp)
           // .clip(RoundedCornerShape(10.dp))
            //.background(Color.White)
            //.verticalScroll(rememberScrollState())
            .padding(horizontal = 0.dp, vertical = 0.dp)
    )
    {


        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
                .background(Color.White)
        )
        {
            Logo()
            Spacer(Modifier.weight(1f))
            AdvancedIcon(
                icon_id = R.drawable.baseline_person_24,
                text = "Profile",
                navController = navController
            )
        }//END Row()

        HeroPanel()
        CategoriesPanel()
        DrawMenuItemsList()

    }//END COL


 }//END HomeScreen




