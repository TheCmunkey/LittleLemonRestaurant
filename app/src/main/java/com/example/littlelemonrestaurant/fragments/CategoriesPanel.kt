package com.example.littlelemonrestaurant.fragments

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor

var savedCategorySelected: String = ""

@Composable
fun CategoriesPanel() {

    val categories = listOf<String>("starters","mains", "desserts")
    var categorySelected by rememberSaveable { mutableStateOf(savedCategorySelected) }

    var colors : Color

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
            .shadow(12.dp)
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .background(Color.White)
            //.verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp, vertical = 4.dp)
    )
    {
        Row (modifier = Modifier.fillMaxWidth())
        {
            Spacer(Modifier.weight(1f))
            categories.forEach() {

                var colors = if (categorySelected==it) LittleLemonColor.Yellow
                else LittleLemonColor.Grey

                Button(
                    modifier = Modifier

                        .requiredHeight(38.dp)
                        .padding(horizontal = 1.dp),

                     colors = ButtonDefaults.buttonColors(colors),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 1.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 4.dp,
                        focusedElevation = 4.dp
                    ),
                    shape = RoundedCornerShape(50),
                    border = BorderStroke(1.dp, color = Color(0x1F000000)),
//  interactionSource = interactionSource,
                    onClick = {
                        categorySelected = if(categorySelected==it) ""
                        else it

                        savedCategorySelected = categorySelected
                        savedLiveTrigger.postValue(!savedLiveTrigger.value!!)
                    }
                )
                {
                    Text(it)
                }
                Spacer(Modifier.weight(1f))
            }


        }//END Row
    }//END Column
}

