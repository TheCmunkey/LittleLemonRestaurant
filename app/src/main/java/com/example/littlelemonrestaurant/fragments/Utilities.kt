package com.example.littlelemonrestaurant.fragments

import android.content.res.Resources
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.littlelemonrestaurant.MainActivity
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
fun isUserLoggedIn()  : Boolean {

    val sharedPreferences by lazy {
        MainActivity.mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    return sharedPreferences.getBoolean("loggedIn", false)
}
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
fun getFirstScreen() : String {

    ///return(HomeScreen.route)  for testing

    return when (isUserLoggedIn()) {
        true ->  (HomeScreen.route)
        false -> (OnBoardingScreen.route)
    }
}

val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()

