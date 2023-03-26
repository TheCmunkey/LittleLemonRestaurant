package com.example.littlelemonrestaurant

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonrestaurant.fragments.Logo
import com.example.littlelemonrestaurant.fragments.ProfileScreen
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.ui.theme.LittleLemonRestaurantTheme
import com.example.littlelemonrestaurant.ui.theme.buttonModifier
import com.example.littlelemonrestaurant.ui.theme.get_textFieldColors

@Composable
fun OnBoardingScreen(navController: NavHostController) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(LittleLemonColor.White)
            .padding(start = 25.dp, end = 25.dp, top = 30.dp, bottom = 30.dp)
    )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(
                    26.dp, shape = RoundedCornerShape(16.dp), clip = false,
                    ambientColor = Color(0xFFFFFF00), spotColor = Color(0xFFFFFF00)
                )
                .shadow(
                    16.dp, shape = RoundedCornerShape(16.dp), clip = false,
                    ambientColor = Color(0xFFFFFF00), spotColor = Color(0xFFFFFF00)
                )
                .shadow(
                    8.dp, shape = RoundedCornerShape(16.dp), clip = false,
                    ambientColor = Color(0xFF000000), spotColor = Color(0xFF000000)
                )
                .shadow(
                    4.dp, shape = RoundedCornerShape(16.dp), clip = false,
                    ambientColor = Color(0xFF000000), spotColor = Color(0xFF000000)
                )
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.CenterVertically)
                .border(
                    BorderStroke(1.dp, color = Color(0x80000000)),
                    RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 14.dp)
        )
        {

            Column(Modifier.height(78.dp))
            {
                Logo()
            }
            GetStartedSplashScreen()
            Form(navController)
        }
    }//END Box

}//END OnBoardingScreen()

var testWidth = 1.0f
fun shitTest(x: Float) {
    testWidth = x
}

@Composable
fun GetStartedSplashScreen() {

    val lemonTree = ImageBitmap.imageResource(id = R.drawable.lemontree2)
    val srcSize = IntSize(lemonTree.width, lemonTree.height)
    val srcOffset = IntOffset(0, 0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clipToBounds()
            .drawBehind {
                drawImage(
                    lemonTree, srcOffset, srcSize,
                    dstSize = IntSize(
                        this.size.width.toInt(),
                        ((this.size.width / srcSize.width.toFloat()) * srcSize.height.toFloat()).toInt()  //LMFAO!
                    ),
                    colorFilter = ColorFilter.tint(Color(0x9Fe0f500), BlendMode.ColorBurn)
                )

            }

//.background(Color.DarkGray)
    )
    {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xc0005050),
                            Color(0x10008080),
                            Color(0x0500b0b0),
                            Color(0x0000f0f0),
                        )
                    )
                )
        )

        //Text("Width= $testWidth", color = Color(0xFFFFFFFF))

        Spacer(modifier = Modifier.height(10.dp))

        val text = (stringResource(id = R.string.welcome)).split("\n")
        text.forEach {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = LittleLemonColor.White,
                fontWeight = FontWeight(400),
                style = TextStyle(
                    fontSize = 28.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(-2.0f, -2.0f),
                        blurRadius = 5.5f
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(5.dp))
        }//Print all strings forEach

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x0000f0f0),
                            Color(0x0500b0b0),
                            Color(0x10008080),
                            Color(0xc0005050),
                        )
                    )
                )
        )
    }

}//END GetStartedSplashScreen


@Composable
fun Form(navController: NavHostController) {
    val firstNameSaved = MutableLiveData<String>()
    val lastNameSaved = MutableLiveData<String>()
    val emailSaved = MutableLiveData<String>()
    var registerEnabled: Boolean
    var firstNameError: Boolean
    var lastNameError: Boolean
    var emailError: Boolean
    var buttonColor: Color
    var firstNameColor = LittleLemonColor.Black
    var lastNameColor = LittleLemonColor.Black
    var emailColor = LittleLemonColor.Black
    val context = LocalContext.current
    val mainActivity = LocalContext.current as Activity
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    firstNameSaved.value = sharedPreferences.getString("firstName", "")
    lastNameSaved.value = sharedPreferences.getString("lastName", "")
    emailSaved.value = sharedPreferences.getString("email", "")

    registerEnabled = true
    firstNameError = false
    lastNameError = false
    emailError = false

    var errorMessage = "Please enter your "

    if (firstNameSaved.value.isNullOrEmpty()) {
        firstNameError = true
        registerEnabled = false
        errorMessage += "First name. "
        firstNameColor = LittleLemonColor.DarkRed
    }
    if (lastNameSaved.value.isNullOrEmpty()) {
        lastNameError = true
        registerEnabled = false
        errorMessage += "Last name. "
        lastNameColor = LittleLemonColor.DarkRed
    }

    if (emailSaved.value.isNullOrEmpty()) {
        emailError = true
        registerEnabled = false
        errorMessage += "Email address."
    } else {
        if (!emailSaved.value?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() }!!) {
            emailError = true
            registerEnabled = false
            errorMessage += "Valid email address."
        }
    }
    if (emailError) emailColor = LittleLemonColor.DarkRed

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight(1.0f)
            .padding(vertical = 10.dp)
    )
    {

        val focusManager = LocalFocusManager.current

//FIRST NAME
        Spacer(modifier = Modifier.height(12.dp))

        val first = firstNameSaved.observeAsState("")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        {
            Text(
                text = stringResource(id = R.string.enter_first_name),
                color = firstNameColor,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(12.dp))
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
                modifier = buttonModifier(58.dp, 1.0f),
                textStyle = TextStyle(fontSize = 20.sp),
                colors = get_textFieldColors(),
                shape = RoundedCornerShape(100),
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                keyboardActions = KeyboardActions {
                    // focusManager.moveFocus(FocusDirection.Down)
                    focusManager.clearFocus()
                },
            )
        }//END Row firstName

//LAST NAME:
        Spacer(modifier = Modifier.height(12.dp))

        val last = lastNameSaved.observeAsState("")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        {
            Text(
                text = stringResource(id = R.string.enter_last_name),
                color = lastNameColor,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
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
                modifier = buttonModifier(58.dp, 1.0f),
                textStyle = TextStyle(fontSize = 20.sp),
                colors = get_textFieldColors(),
                shape = RoundedCornerShape(100),
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                    //focusManager.moveFocus(FocusDirection.Down)
                },
            )

        }//END Row lastName

////EMAIL

        Spacer(modifier = Modifier.height(12.dp))

        val email = emailSaved.observeAsState("")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )
        {
            Text(
                text = stringResource(id = R.string.enter_email),
                color = emailColor,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
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
                modifier = buttonModifier(58.dp, 1.0f),
                textStyle = TextStyle(fontSize = 20.sp),
                colors = get_textFieldColors(),
                shape = RoundedCornerShape(100),
                singleLine = true,
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },

                )
        }//END Row email

//REGISTER
        Spacer(modifier = Modifier.height(16.dp))

    }//END Column

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth(0.9f)
    )
    {
        buttonColor = if (registerEnabled) LittleLemonColor.Yellow
        else Color.DarkGray

        Button(
            onClick = {
                if (registerEnabled) {
                    sharedPreferences.edit(commit = true) {
                        putBoolean("loggedIn", true)
                    }
                    navController.navigate(ProfileScreen.route)
                } else {
                    Toast.makeText(mainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                }
            },
            colors = ButtonDefaults.buttonColors(buttonColor),
            // interactionSource = interactionSource,
            shape = RoundedCornerShape(100),
            border = BorderStroke(1.dp, color = Color(0x6F000000)),
//            elevation = ButtonDefaults.elevation(
//                defaultElevation = 4.dp,
//                pressedElevation = 1.dp,
//                disabledElevation = 0.dp,
//                hoveredElevation = 4.dp,
//                focusedElevation = 4.dp
//            ),
            modifier = buttonModifier(58.dp, 1.0f),
        )
        {
            Text(
                text = stringResource(id = R.string.register_button),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 24.sp,
                    shadow = Shadow(
                        color = Color(0xAFFFFFFF), offset = Offset(1.0f, 1.0f), blurRadius = 1f
                    )
                ),
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 0.dp, end = 0.dp)
            )
        }
    }//END Column Register

}//END Column

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LittleLemonRestaurantTheme {
        val navController = rememberNavController()
        OnBoardingScreen(navController)
    }
}