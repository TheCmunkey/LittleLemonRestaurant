package com.example.littlelemonrestaurant

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.fragments.DeleteProfileDialogBox
import com.example.littlelemonrestaurant.fragments.TopNavBar
import com.example.littlelemonrestaurant.ui.theme.DEFAULT_SCREEN_PADDING_HORIZONTAL
import com.example.littlelemonrestaurant.ui.theme.DeleteProfileButtonModifier
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor

data class Post(val openDialog: MutableState<Boolean> = mutableStateOf(false))

val LocalPost = compositionLocalOf { Post(openDialog = mutableStateOf(false)) }

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ProfileScreen(navController: NavHostController) {

    val openDialog =
        Post(openDialog = remember { mutableStateOf(false) }) // Initially dialog is closed

    Column(
        modifier = Modifier
            .fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
    )
    {

        TopNavBar(navController, "HomeScreen")

        Box(
            modifier = Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(1.0f)
        )
        {

            CompositionLocalProvider(LocalPost provides openDialog) {
                PrintUserProfileInfo()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(0.dp)
                    .height(240.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0xc0004040),
                                Color(0x70004040),
                                Color(0x30001010),
                                Color(0x10001010),
                                Color(0x00001010),
                            )
                        )
                    )
            )
        }//END Box
    }//END Colum

    if (openDialog.openDialog.value) {
        DeleteProfileDialogBox(navController) { openDialog.openDialog.value = false }
    }

}//END HomeScreen

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun PrintUserProfileInfo() {

    val openDialog = LocalPost.current

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    var buttonColor: Color = LittleLemonColor.Yellow
    if (isPressed) buttonColor = LittleLemonColor.DarkGray

    val mainActivity = MainActivity.mainActivity
    val sharedPreferences by lazy {
        mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    val firstNameSaved = MutableLiveData<String>()
    val lastNameSaved = MutableLiveData<String>()
    val emailSaved = MutableLiveData<String>()

    firstNameSaved.value = sharedPreferences.getString("firstName", "")
    lastNameSaved.value = sharedPreferences.getString("lastName", "")
    emailSaved.value = sharedPreferences.getString("email", "")

    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .fillMaxWidth(1.0f)
                .fillMaxHeight(1.0f)
                .padding(top = 32.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            LittleLemonColor.White,
                            LittleLemonColor.Grey,
                        )
                    )
                )
        )
        {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .shadow(8.dp)
                    .fillMaxHeight(0.8f)
                    .verticalScroll(rememberScrollState())
                    .background(LittleLemonColor.White)
                    .border(BorderStroke(1.dp, color = Color(0x6F000000)))
                    .padding(
                        start = DEFAULT_SCREEN_PADDING_HORIZONTAL,
                        end = DEFAULT_SCREEN_PADDING_HORIZONTAL,
                    )
            )
            {

                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.align(Alignment.CenterHorizontally))
                {
                    PrintProfileField("Your Profile", 30.sp, 500)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.5f)
                )
                {
                    Divider(
                        color = LittleLemonColor.Grey,
                        thickness = 2.dp,
                        startIndent = 0.dp
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                PrintProfileField("First Name : " + firstNameSaved.value!!, 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Last Name : " + lastNameSaved.value!!, 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Email : " + emailSaved.value!!, 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Street : 321 5th Ave", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("City : Bronx", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("State : New York", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Zip : 10020", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Phone : (631)523-7769", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Credit Card Type : Visa", 24.sp, 400)

                Spacer(modifier = Modifier.height(10.dp))
                PrintProfileField("Card Number (xxxx) :  3251", 24.sp, 400)

                Spacer(modifier = Modifier.height(20.dp))

            }//END COLUMN

//LOGOUT BUTTON

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    openDialog.openDialog.value = true
                },
                contentPadding= PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                modifier = DeleteProfileButtonModifier(44.dp),
                colors = ButtonDefaults.buttonColors(buttonColor),
                interactionSource = interactionSource,
                shape = RoundedCornerShape(100),
                border = BorderStroke(1.dp, color = Color(0x6F000000)),
            )
            {
                Text(
                    text = stringResource(id = R.string.log_out),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 20.sp,
                        shadow = Shadow(
                            color = Color(0xAFFFFFFF), offset = Offset(1.0f, 1.0f), blurRadius = 1f
                        )
                    ),
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(start = 0.dp, end = 0.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

        }//END Column
    }
}//END

@Composable
fun PrintProfileField(text: String, fontSize: TextUnit, fontWeight: Int) {

    Box()
    {
        Text(
            text = text,
            color = Color.Black,
            style = TextStyle(
                fontSize = fontSize,
                shadow = Shadow(
                    color = Color.White,
                    offset = Offset(0.5f, 0.5f),
                    blurRadius = 2.7f
                )
            ),
            fontWeight = FontWeight(fontWeight),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
    }

}//END PrintProfileField

