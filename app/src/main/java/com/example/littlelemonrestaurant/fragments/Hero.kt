package com.example.littlelemonrestaurant.fragments

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.brush_1

@Preview
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
                .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(percent = 8))
                .background(color = LittleLemonColor.Grey)
        }
        else ->
        {
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 12.dp)
                .clip(RoundedCornerShape(percent = 8))
                .background(color = LittleLemonColor.Grey)
        }
    }

    Column(modifier = modifier)
    {

        Row()
        {

            Column(modifier = Modifier.fillMaxWidth(0.5f))
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
                        .padding(start = 36.dp, top = 45.dp, bottom = 0.dp, end = 26.dp)
                        .shadow(
                            elevation = 27.dp,
                            shape = RoundedCornerShape(10.dp),
                            clip = false,
                            ambientColor = LittleLemonColor.Yellow,
                            spotColor = LittleLemonColor.Yellow
                        )
                        .clip(RoundedCornerShape(10.dp))
                )
            }

        }//END Row()


        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(buttoncolor),
            interactionSource = interactionSource,
            shape = RoundedCornerShape(28),
            border = BorderStroke(1.dp,color= Color(0x8F000000)),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 4.dp,
                pressedElevation = 1.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 4.dp,
                focusedElevation = 4.dp
            ),
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
        )
        {
            Text(
                text = stringResource(id = R.string.order_delivery),
                color = fontcolor,
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 0.dp, end = 0.dp)
            )
        }

    }//END Column
}//END UpperPanel

@Preview(showBackground = true)
@Composable
fun HeroPreview()
{
    HeroPanel()
}
