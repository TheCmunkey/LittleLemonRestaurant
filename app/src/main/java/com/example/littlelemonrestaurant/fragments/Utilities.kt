package com.example.littlelemonrestaurant.fragments

import android.app.Activity
import android.content.res.Resources
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import com.example.littlelemonrestaurant.MainActivity

@Composable
fun isUserLoggedIn()  : Boolean {

    val mainActivity = LocalContext.current as Activity
    val sharedPreferences by lazy {
                 mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    return sharedPreferences.getBoolean("loggedIn", false)
}

@Composable
fun getFirstScreen() : String {

    ///return(HomeScreen.route)  for testing

    return when (isUserLoggedIn()) {
        true ->  (HomeScreen.route)
        false -> (OnBoardingScreen.route)
    }
}

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

