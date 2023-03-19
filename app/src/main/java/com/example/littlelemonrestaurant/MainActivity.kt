package com.example.littlelemonrestaurant


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonrestaurant.fragments.*

import com.example.littlelemonrestaurant.ui.theme.LittleLemonRestaurantTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        mainActivity = this

        val startDestination = getFirstScreen()

        setContent {
             LittleLemonRestaurantTheme {

                 Box(modifier = Modifier.fillMaxSize())
                 {
                     val navController = rememberNavController()


                     NavHost(navController = navController, startDestination = startDestination)
                     {

                         composable(HomeScreen.route)       { HomeScreen(navController) }
                         composable(OnBoardingScreen.route) { OnBoardingScreen(navController) }
                         composable(ProfileScreen.route)    { ProfileScreen(navController) }
//                         composable(
//                             DishDetails.route + "/{${DishDetails.argDishId}}",
//                             arguments = listOf(navArgument(DishDetails.argDishId) {
//                                 type = NavType.IntType
//                             })
//                         )
//                         {
//                             val id =
//                                 requireNotNull(it.arguments?.getInt(DishDetails.argDishId)) { "Dish id is null" }
//                             DishDetails(navController, id)
//                         }
                     }//END NavHost

                 }//END Box()
             }//END LittleLemonRestaurantTheme
        }//END setContent
    }//END onCreate


    companion object {
        lateinit var mainActivity : MainActivity
    }
}//END MainActivity


