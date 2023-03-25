package com.example.littlelemonrestaurant.fragments

import com.example.littlelemonrestaurant.R

interface Destinations {
    val route: String
    val iconId: Int
}

object HomeScreen : Destinations {
    override val route = "HomeScreen"
    override val iconId = R.drawable.baseline_home_24
}

object OnBoardingScreen : Destinations {
    override val route = "OnBoardingScreen"
    override val iconId = 0
}

object ProfileScreen : Destinations {
    override val route = "ProfileScreen"
    override val iconId = R.drawable.profile
}

val Screens = mapOf(
    "HomeScreen" to HomeScreen,
    "OnBoardingScreen" to OnBoardingScreen,
    "ProfileScreen" to ProfileScreen
)