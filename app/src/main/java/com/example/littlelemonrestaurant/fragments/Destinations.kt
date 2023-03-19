package com.example.littlelemonrestaurant.fragments

interface Destinations
{
    val route: String
}

object HomeScreen : Destinations
{
    override val route = "HomeScreen"
}

object OnBoardingScreen : Destinations
{
    override val route = "OnBoardingScreen"
}

object ProfileScreen : Destinations
{
    override val route = "ProfileScreen"
}
