package com.example.littlelemonrestaurant.fragments

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.MutableLiveData
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.brush_1
import com.example.littlelemonrestaurant.ui.theme.get_textFieldColors

var savedSearchPhrase: String = ""
var savedOrderMenuItems: Boolean = false

val savedLiveTrigger = MutableLiveData<Boolean>()

@Composable
fun HeroPanel()
{

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttoncolor = if (isPressed) LittleLemonColor.Green else LittleLemonColor.Yellow
    val fontcolor   = if (isPressed) LittleLemonColor.White else LittleLemonColor.Black
    val configuration = LocalConfiguration.current
    var modifier: Modifier

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE ->
        {
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight()
                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                .clip(RoundedCornerShape(percent = 8))
                .background(color = LittleLemonColor.Grey)
        }
        else ->
        {
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                //.clip(RoundedCornerShape(percent = 8))
                .background(color = LittleLemonColor.Grey)
        }
    }

    Column( modifier = modifier)
    {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .zIndex(100000.0f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x50000000),
                            Color(0x10000000),
                            Color(0x00100000),
                            Color(0x00000000),
                        )
                    ))
        )
        Row()
        {

            Column(modifier = Modifier.fillMaxWidth(0.6f))
            {

                DropShadowText(
                    text = stringResource(id = R.string.little_lemon),
                    fontSize = 34.sp,
                    fontWeight = 500,
                    modifierTop = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                        .textGradientBrush(brush_1),
                    modifierBot = Modifier.padding(start = 16.dp, top = 16.dp),
                    textcolor = LittleLemonColor.Yellow,
                    alpha = 0.5f
                )

                DropShadowText(
                    text = stringResource(id = R.string.location),
                    fontSize = 20.sp,
                    fontWeight = 500,
                    modifierTop = Modifier.padding(start = 16.dp, top=6.dp),
                    modifierBot = Modifier.padding(start = 16.dp, top=6.dp),
                    textcolor = LittleLemonColor.Black,
                    shadowcolor = LittleLemonColor.Yellow,
                    offsetY = -(1.dp),
                    alpha = 0.50f
                )


                Text(
                    text = stringResource(id = R.string.description),
                    style = TextStyle(lineHeight = 18.sp),
                    fontSize = 14.sp,
                    color = LittleLemonColor.Black,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 12.dp, bottom = 0.dp, end = 10.dp)

                )

            }
            Column(modifier = Modifier.fillMaxWidth(1.0f))
            {
                Image(
                    painter = painterResource(id = R.drawable.herofish),
                    contentDescription = "Upper Panel Image",
                    modifier = Modifier
                        .padding(start = 26.dp, top = 16.dp, bottom = 0.dp, end = 26.dp)
                        .shadow(
                            elevation = 27.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = false,
                            ambientColor = LittleLemonColor.Yellow,
                            spotColor = LittleLemonColor.Yellow
                        )
                        .clip(RoundedCornerShape(10.dp))
                )
            }//END Column (right)

        }//END Row()

        Spacer(modifier = Modifier.height(14.dp))
        SearchBoxAndTitleSort()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .zIndex(100000.0f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            Color(0x01000000),
                            Color(0x10000000),
                            Color(0x40000000),
                        )
                    ))
        )
//        Button(
//            onClick = { },
//            colors = ButtonDefaults.buttonColors(buttoncolor),
//            interactionSource = interactionSource,
//            shape = RoundedCornerShape(28),
//            border = BorderStroke(1.dp,color= Color(0x8F000000)),
//            elevation = ButtonDefaults.elevation(
//                defaultElevation = 4.dp,
//                pressedElevation = 1.dp,
//                disabledElevation = 0.dp,
//                hoveredElevation = 4.dp,
//                focusedElevation = 4.dp
//            ),
//            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
//        )
//        {
//            Text(
//                text = stringResource(id = R.string.order_delivery),
//                color = fontcolor,
//                fontSize = 18.sp,
//                fontWeight = FontWeight(600),
//                modifier = Modifier.padding(start = 0.dp, end = 0.dp)
//            )
//        }

    }//END Column
}//END UpperPanel


@Composable
private fun SearchBoxAndTitleSort() {

    var orderMenuItems by rememberSaveable { mutableStateOf(savedOrderMenuItems) }
    var searchPhrase by rememberSaveable { mutableStateOf(savedSearchPhrase) }

    val focusManager = LocalFocusManager.current

    var colors = if (orderMenuItems!!) LittleLemonColor.Yellow
    else Color(0xFFAAAAAA)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
           modifier= Modifier
            .fillMaxWidth(1.0f))
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            OutlinedTextField(
                modifier = Modifier
                    .height(55.dp)
                    //.requiredSize(55.dp)
                    .border(BorderStroke(1.dp, color = Color(0x1F000000)),
                            RoundedCornerShape(100))
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
                    .padding(horizontal = 1.dp, vertical = 0.dp),

                textStyle = TextStyle(fontSize = 18.sp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = LittleLemonColor.Black
                    )
                },
                colors = get_textFieldColors(),
                shape = RoundedCornerShape(100),
                value = searchPhrase,
                onValueChange = {
                    searchPhrase = it
                    savedSearchPhrase = searchPhrase
                    savedLiveTrigger.postValue(!savedLiveTrigger.value!!)
                },
                //label = { Text("Search") },
                singleLine = true,
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
            )
            Button(
                modifier = Modifier
                    .requiredSize(52.dp)
                    .requiredHeight(52.dp)
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

                    orderMenuItems = !orderMenuItems
                    savedOrderMenuItems = orderMenuItems
                    savedLiveTrigger.postValue(!savedLiveTrigger.value!!)
                }
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_sort_by_alpha_24),
                    modifier = Modifier,
                    contentDescription = "Sort"
                )
            }
        }
    }
}//END SearchBoxAndTitleSort