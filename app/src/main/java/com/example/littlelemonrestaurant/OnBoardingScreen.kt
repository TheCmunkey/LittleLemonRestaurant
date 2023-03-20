package com.example.littlelemonrestaurant

import android.content.res.Configuration
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonrestaurant.fragments.ProfileScreen
import com.example.littlelemonrestaurant.fragments.Logo
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.LittleLemonRestaurantTheme
import com.example.littlelemonrestaurant.ui.theme.get_textFieldColors


@Composable
fun OnBoardingScreen(navController: NavHostController)
{

    val firstNameSaved = MutableLiveData<String>()
    val lastNameSaved = MutableLiveData<String>()
    val emailSaved = MutableLiveData<String>()
    var registerEnabled: Boolean
    var firstNameError: Boolean
    var lastNameError: Boolean
    var emailError: Boolean
    var buttonColor: Color
    val configuration = LocalConfiguration.current


    val mainActivity = MainActivity.mainActivity
    val sharedPreferences by lazy {
        mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    firstNameSaved.value = sharedPreferences.getString("firstName", "")
    lastNameSaved.value = sharedPreferences.getString("lastName", "")
    emailSaved.value = sharedPreferences.getString("email", "")

    registerEnabled = true
    firstNameError = false
    lastNameError = false
    emailError = false

    var errorMessage = "Please enter your "

    if (firstNameSaved.value.isNullOrEmpty())
    {
        firstNameError = true
        registerEnabled = false
        errorMessage+="First name. "
    }
    if (lastNameSaved.value.isNullOrEmpty())
    {
        lastNameError = true
        registerEnabled = false
        errorMessage+="Last name. "
    }

    if (emailSaved.value.isNullOrEmpty())
    {
        emailError = true
        registerEnabled = false
        errorMessage+="Email address."
    }
    else {
        if (!emailSaved.value?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() }!!) {
            emailError = true
            registerEnabled = false
            errorMessage+="Valid email address."
        }
    }



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
                .padding(vertical = 14.dp)
        )
        {

            val focusManager = LocalFocusManager.current

            Logo()

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
            )
            {
                Text(
                    text = stringResource(id = R.string.welcome),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = LittleLemonColor.Yellow,
                    fontWeight = FontWeight(400),
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(-1.0f,-1.0f), blurRadius = 0.5f
                        )),
                    modifier = Modifier.padding (horizontal = 32.dp, vertical = 32.dp )
                )
            }

//FIRST NAME
            Spacer(modifier = Modifier.height(16.dp))

            val first = firstNameSaved.observeAsState("")

            OutlinedTextField(
                isError = firstNameError,
                value = first.value,

                onValueChange = {
                    sharedPreferences.edit(commit = true) {
                        putString("firstName", it)
                    }
                    mainActivity.runOnUiThread {
                        firstNameSaved.value = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 24.sp),
                colors = get_textFieldColors(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                    ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.enter_first_name),
                        fontSize = 24.sp
                    )

                })

//LAST NAME:
            Spacer(modifier = Modifier.height(16.dp))

            val last = lastNameSaved.observeAsState("")

            OutlinedTextField(
                isError = lastNameError,
                value = last.value,
                onValueChange = {
                    sharedPreferences.edit(commit = true) {
                        putString("lastName", it)
                    }
                    mainActivity.runOnUiThread {
                        lastNameSaved.value = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 24.sp),
                colors = get_textFieldColors(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.enter_last_name),
                        fontSize = 24.sp
                    )
                })

////EMAIL

            Spacer(modifier = Modifier.height(16.dp))

            val email = emailSaved.observeAsState("")

            OutlinedTextField(
                isError = emailError,
                value = email.value,
                onValueChange = {
                    sharedPreferences.edit(commit = true) {
                        putString("email", it)
                    }
                    mainActivity.runOnUiThread {
                        emailSaved.value = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 24.sp),
                colors = get_textFieldColors(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.enter_email),
                        fontSize = 24.sp
                    )
                })

//REGISTER
            Spacer(modifier = Modifier.height(28.dp))

            buttonColor = if (registerEnabled) LittleLemonColor.Yellow
            else Color.DarkGray

            Button(
                onClick = {
                      if(registerEnabled)
                      {
                          sharedPreferences.edit(commit = true) {
                              putBoolean("loggedIn", true)
                          }
                          navController.navigate(ProfileScreen.route)
                      }
                    else
                      {
                          Toast.makeText( mainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                      }
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
                    .padding(start = 16.dp, end = 16.dp,bottom = 16.dp)
                    .fillMaxWidth()
            )
            {
                Text(
                    text = stringResource(id = R.string.register_button),
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Green, offset = Offset(1.0f,1.0f), blurRadius = 1f
                        )),
                      fontWeight = FontWeight(600),
                    modifier = Modifier.padding(start = 0.dp, end = 0.dp)
                )
            }
        }//END Column
    }//END Box

}//END OnBoardingScreen()

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    LittleLemonRestaurantTheme {
        val navController = rememberNavController()
        OnBoardingScreen(navController)
    }
}