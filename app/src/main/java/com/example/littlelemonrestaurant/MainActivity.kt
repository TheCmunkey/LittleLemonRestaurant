package com.example.littlelemonrestaurant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemonrestaurant.fragments.*
import com.example.littlelemonrestaurant.ui.theme.LittleLemonRestaurantTheme
import com.google.gson.GsonBuilder
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




class MainActivity : ComponentActivity() {

    private val menuItemListLiveData = MutableLiveData<MenuNetwork>()

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("application", "json"))

        }
    }
    val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        savedLiveTrigger.postValue(false)

        mainActivity = this

        //this.deleteDatabase("database");
        val startDestination = getFirstScreen()

        setContent {
             LittleLemonRestaurantTheme {

                 Box(modifier = Modifier.fillMaxSize())
                 {

                     val navController = rememberNavController()

                     NavHost(navController = navController, startDestination = startDestination)
                     {

                         composable(HomeScreen.route){
                             HomeScreen(navController)
                         }
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

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menu = MenuNetwork(fetchMenu())
                saveMenuToDatabase(menu.menu)
                runOnUiThread {
                    menuItemListLiveData.value = menu



                }
            }
        }
    }//END onCreate


    companion object {
        lateinit var mainActivity : MainActivity


    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {

        val response: MenuNetwork =
            httpClient.get("https://marcfetterer.com/little-lemon/main/menu.json")
                .body()


        return response.menu ?: listOf()

    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {

        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

}//END MainActivity



