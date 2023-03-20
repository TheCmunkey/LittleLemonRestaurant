package com.example.littlelemonrestaurant

import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.fragments.AdvancedIcon
import com.example.littlelemonrestaurant.fragments.Logo
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor

@Composable
fun ProfileScreen(navController: NavHostController)
{
    val firstNameSaved = MutableLiveData<String>()
    val lastNameSaved = MutableLiveData<String>()
    val emailSaved = MutableLiveData<String>()
    var buttonColor: Color
    val configuration = LocalConfiguration.current

    val mainActivity = MainActivity.mainActivity
    val sharedPreferences by lazy {
        mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    firstNameSaved.value = sharedPreferences.getString("firstName", "")
    lastNameSaved.value  = sharedPreferences.getString("lastName", "")
    emailSaved.value     = sharedPreferences.getString("email", "")

    val contentScale = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        ContentScale.FillWidth
    }
    else {
        ContentScale.FillHeight
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.lemontree2),
                contentScale = contentScale
            )
            .padding(start = 25.dp, end = 25.dp, top = 50.dp, bottom = 50.dp)
    )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 14.dp)
        )
        {


            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Logo()
                Spacer(Modifier.weight(1f))
                AdvancedIcon(
                    icon_id = R.drawable.ic_little_lemon,
                    text = "Home",
                    navController = navController
                )
            }
            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                   LittleLemonColor.Green,

                                LittleLemonColor.DarkGray,
                            )
                        )
                    )

            )
            {

                Spacer(modifier = Modifier.height(32.dp))
                PrintProfileField("Your Profile")

                Spacer(modifier = Modifier.height(16.dp))
                PrintProfileField("First Name : "+firstNameSaved.value!!)

                Spacer(modifier = Modifier.height(16.dp))
                PrintProfileField("Last Name : "+lastNameSaved.value!!)

                Spacer(modifier = Modifier.height(16.dp))
                PrintProfileField("Email : "+emailSaved.value!!)

                Spacer(modifier = Modifier.height(16.dp))
                PrintProfileField("Address : 215 3rd Ave Bronx, New York")

                Spacer(modifier = Modifier.height(16.dp))
                PrintProfileField("Phone : 929-324-4567")

                Spacer(modifier = Modifier.height(32.dp))

            }
//LOGOUT BUTTOM

            Spacer(modifier = Modifier.height(18.dp))

            buttonColor = LittleLemonColor.Yellow

            Button(
                onClick = {
                    sharedPreferences.edit().remove("firstName").apply();
                    sharedPreferences.edit().remove("lastName").apply();
                    sharedPreferences.edit().remove("email").apply();
                    sharedPreferences.edit().remove("loggedIn").apply();

                    navController.navigate(com.example.littlelemonrestaurant.fragments.OnBoardingScreen.route)
                },
                colors = ButtonDefaults.buttonColors(buttonColor),
                // interactionSource = interactionSource,
                shape = RoundedCornerShape(16),
                border = BorderStroke(1.dp, color = Color.DarkGray),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 1.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 4.dp,
                    focusedElevation = 4.dp
                ),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
            {
                Text(
                    text = stringResource(id = R.string.log_out),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Green, offset = Offset(1.0f, 1.0f), blurRadius = 1f
                        )
                    ),
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(start = 0.dp, end = 0.dp)
                )

            }//END Logout Button
        }//END Column

    }//END Box

}//END HomeScreen
@Composable
fun PrintProfileField(text: String)
{
    Box()
    {
        Text(text = text,
            color = Color.White,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = Color.Black, offset = Offset(0.5f,0.5f), blurRadius = 1f
                )
            ),
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
    }

}