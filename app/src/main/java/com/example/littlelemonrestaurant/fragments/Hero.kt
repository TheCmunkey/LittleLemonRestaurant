package com.example.littlelemonrestaurant.fragments

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.DEFAULT_SCREEN_PADDING_HORIZONTAL
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.brush_1
import com.example.littlelemonrestaurant.ui.theme.get_textFieldColors

var savedSearchPhrase: String = ""
var savedOrderMenuItems: Boolean = false
val savedLiveTrigger = MutableLiveData<Boolean>()

var fullHeight = 0
fun fuck(x: Int) {
    fullHeight = x
}

@Composable
fun HeroPanel() {

    val interactionSource = remember { MutableInteractionSource() }
    val configuration = LocalConfiguration.current

    val modifier: Modifier =
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) Modifier
        else Modifier.fillMaxHeight(1.0f)

    Box()
    {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.background(color = LittleLemonColor.Grey)
        )
        {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0x50000000),
                                Color(0x10000000),
                                Color(0x00100000),
                                Color(0x00000000),
                            )
                        )
                    )
            )
            Row()
            {

                Column(modifier = Modifier.fillMaxWidth(0.6f))
                {

                    DropShadowText(
                        text = stringResource(id = R.string.little_lemon),
                        fontSize = 30.sp,
                        fontWeight = 400,
                        modifierTop = Modifier
                            .padding(start = DEFAULT_SCREEN_PADDING_HORIZONTAL, top = 0.dp)
                            .textGradientBrush(brush_1),
                        modifierBot = Modifier.padding(
                            start = DEFAULT_SCREEN_PADDING_HORIZONTAL - 2.dp,
                            top = (0.dp)
                        ),
                        textcolor = LittleLemonColor.Yellow,
                        alpha = 0.5f
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    DropShadowText(
                        text = stringResource(id = R.string.location),
                        fontSize = 20.sp,
                        fontWeight = 500,
                        modifierTop = Modifier.padding(
                            start = DEFAULT_SCREEN_PADDING_HORIZONTAL + 2.dp,
                            top = 0.dp
                        ),
                        modifierBot = Modifier.padding(
                            start = DEFAULT_SCREEN_PADDING_HORIZONTAL + 2.dp,
                            top = 0.dp
                        ),
                        textcolor = LittleLemonColor.Black,
                        shadowcolor = LittleLemonColor.Yellow,
                        offsetX = -(1.dp),
                        offsetY = -(1.dp),
                        alpha = 0.30f
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = stringResource(id = R.string.description),
                        style = TextStyle(lineHeight = 18.sp),
                        fontSize = 16.sp,
                        color = LittleLemonColor.Black,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        modifier = Modifier
                            .padding(
                                start = DEFAULT_SCREEN_PADDING_HORIZONTAL,
                                top = 0.dp,
                                bottom = 0.dp,
                                end = 10.dp
                            )

                    )

                }
                val focusRequester = remember { FocusRequester() }
                Column(modifier = Modifier.fillMaxWidth(1.0f))
                {
                    Image(
                        painter = painterResource(id = R.drawable.herofish),
                        contentDescription = "Upper Panel Image",
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .focusTarget()
                            .clickable(onClick = { focusRequester.captureFocus() })
                            .padding(
                                start = 6.dp,
                                top = 6.dp,
                                bottom = 14.dp,
                                end = DEFAULT_SCREEN_PADDING_HORIZONTAL
                            )
                            .border(
                                BorderStroke(1.dp, color = Color(0x7F000000)),
                                RoundedCornerShape(10.dp)
                            )
                            .shadow(
                                elevation = 27.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = false,
                                ambientColor = LittleLemonColor.Yellow,
                                spotColor = LittleLemonColor.Yellow
                            )
                            .shadow(
                                elevation = 27.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = false,
                                ambientColor = LittleLemonColor.Yellow,
                                spotColor = LittleLemonColor.Yellow
                            )
                            .shadow(
                                elevation = 7.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = false,
                                ambientColor = LittleLemonColor.Black,
                                spotColor = LittleLemonColor.Black
                            )
                            .clip(RoundedCornerShape(10.dp))
                    )
                }//END Column (right)

            }//END Row()

            Spacer(modifier = Modifier.height(12.dp))
            SearchBoxAndTitleSort()

            CategoriesPanel()

        }//END Column

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Divider(
                Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxHeight(1.0f)
                    .width(1.dp),
                color = (Color(0xaF000000)),
            )
        }
    }//END Box
}//END UpperPanel


@Composable
private fun SearchBoxAndTitleSort() {

    var orderMenuItems by rememberSaveable { mutableStateOf(savedOrderMenuItems) }
    var searchPhrase by rememberSaveable { mutableStateOf(savedSearchPhrase) }

    val focusManager = LocalFocusManager.current

    val colors = if (orderMenuItems) LittleLemonColor.Yellow
    else LittleLemonColor.LightGrey

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(1.0f)
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            OutlinedTextField(
                modifier = Modifier
                    .height(55.dp)
                    //.requiredSize(55.dp)
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
                    .padding(horizontal = 1.dp)
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
                    ),
                colors = ButtonDefaults.buttonColors(colors),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 1.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 4.dp,
                    focusedElevation = 4.dp
                ),
                shape = RoundedCornerShape(50),
                border = BorderStroke(1.dp, color = Color(0x60000000)),
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