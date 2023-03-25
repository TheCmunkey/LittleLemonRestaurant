package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.leftShadowBrush
import com.example.littlelemonrestaurant.ui.theme.rightShadowBrush

var savedCategorySelected: String = ""

var CategoryVerticalPadding = 8.dp
var CategoryButtonHeight = 38.dp

@Composable
fun CategoriesPanel() {

    val categories = listOf("starters", "salads", "soups", "mains", "desserts", "beverages")
    var categorySelected by rememberSaveable { mutableStateOf(savedCategorySelected) }
//  val interactionSource = remember { MutableInteractionSource() }

    Column(
        Modifier
            .fillMaxWidth(1.0f)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            Color(0x01000000),
                            Color(0x10000000),
                            Color(0x40000000),
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .height(intrinsicSize = IntrinsicSize.Max)
        )
        {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .shadow(22.dp)
                    .border(
                        BorderStroke(1.dp, color = Color(0xfF000000)),
                    )
                    .fillMaxWidth(1.0f)
                    .background(LittleLemonColor.Grey)
                    .padding(horizontal = 0.dp, vertical = CategoryVerticalPadding)

            )
            {

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                )
                {

                    Spacer(Modifier.weight(1f))
                    categories.forEach {

                        val colors = if (categorySelected == it) LittleLemonColor.Yellow
                        else LittleLemonColor.Grey

                        Button(
                            modifier = Modifier

                                .requiredHeight(CategoryButtonHeight)
                                .padding(horizontal = 11.dp),

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
                                categorySelected = if (categorySelected == it) ""
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

            }//Column

            ShadowBox(
                Modifier
                    .fillMaxHeight(1.0f)
                    .width(40.dp),
                leftShadowBrush
            )

            ShadowBox(
                Modifier
                    .align(Alignment.TopEnd)
                    .fillMaxHeight(1.0f)
                    .width(30.dp),
                rightShadowBrush
            )

        }//END Box
    }
}

