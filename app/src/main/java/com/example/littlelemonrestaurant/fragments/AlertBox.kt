package com.example.littlelemonrestaurant.fragments

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.MainActivity
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun DeleteProfileDialogBox(navController: NavHostController, onDismiss: () -> Unit) {

    val contextForToast = LocalContext.current.applicationContext
    val mainActivity = MainActivity.mainActivity
    val sharedPreferences by lazy {
        mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(BorderStroke(1.dp, color = Color(0x6FF00000))),
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(color = LittleLemonColor.DarkGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxHeight(0.7f)
                            .padding(top = 16.dp, bottom = 16.dp),
                        tint = LittleLemonColor.DarkRed
                    )

                }

                Text(
                    modifier = Modifier.padding(top = 26.dp, bottom = 16.dp),
                    text = "This user profile will be permanently deleted",
                    textAlign = TextAlign.Center,
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp, start = 36.dp, end = 36.dp, bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF35898f)),
                    onClick = {
                    sharedPreferences.edit().remove("firstName").apply()
                    sharedPreferences.edit().remove("lastName").apply()
                    sharedPreferences.edit().remove("email").apply()
                    sharedPreferences.edit().remove("loggedIn").apply()
                    navController.navigate(OnBoardingScreen.route)
                        onDismiss()
                        Toast.makeText(
                            contextForToast,
                            "Account Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
                {
                    Text(
                        text = "Delete Profile",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                TextButton(
                    onClick = {
                        onDismiss()
                        Toast.makeText(
                            contextForToast,
                            "Action Cancelled",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    Text(
                        text = "Cancel this action",
                        color = Color(0xFF35898f),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
//
//@Composable
//fun ButtonClick(
//    buttonText: String,
//    onButtonClick: () -> Unit
//) {
//    Button(
//        shape = RoundedCornerShape(5.dp),
//        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
//        onClick = {
//            onButtonClick()
//        }) {
//        Text(
//            text = buttonText,
//            fontSize = 16.sp,
//            color = Color.White
//        )
//    }
//}