package com.example.littlelemonrestaurant.fragments

import androidx.activity.ComponentActivity
import com.example.littlelemonrestaurant.MainActivity

fun isUserLoggedIn()  : Boolean {

    val sharedPreferences by lazy {
        MainActivity.mainActivity.getSharedPreferences("LittleLemon", ComponentActivity.MODE_PRIVATE)
    }

    return sharedPreferences.getBoolean("loggedIn", false)
}

fun getFirstScreen() : String {


    return when (isUserLoggedIn()) {
        true ->  (HomeScreen.route)
        false -> (OnBoardingScreen.route)
    }
}
