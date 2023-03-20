package com.example.littlelemonrestaurant

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.littlelemonrestaurant.fragments.AdvancedIcon
import com.example.littlelemonrestaurant.fragments.HeroPanel
import com.example.littlelemonrestaurant.fragments.Logo
import com.example.littlelemonrestaurant.fragments.MenuItemRoom
import com.example.littlelemonrestaurant.ui.theme.get_textFieldColors

@Composable
fun HomeScreen(navController: NavHostController)
{
    val mainActivity = MainActivity.mainActivity
    val configuration = LocalConfiguration.current
    var orderMenuItems by remember { mutableStateOf(false) }
    var searchPhrase by remember { mutableStateOf("") }



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
            .padding(start = 25.dp, end = 25.dp, top = 25.dp, bottom = 25.dp)
    )
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(12.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                //.verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 14.dp)
        )
        {


            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Logo()
                Spacer(Modifier.weight(1f))
                AdvancedIcon(
                    icon_id = R.drawable.ic_little_lemon,
                    text = "Profile",
                    navController = navController
                )
            }//END Row()

            HeroPanel()

            val focusManager = LocalFocusManager.current

            var colors = if (orderMenuItems) Color(0xFFAAAAAA)
            else Color(0xFFF4CE14)

            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(colors),
                onClick = {
                    orderMenuItems = !orderMenuItems
                }
            )
            {
                Text("Tap To Order By Name")
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 24.sp),
                colors = get_textFieldColors(),
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                label = { Text("Search") },
                singleLine = true,
                keyboardActions = KeyboardActions {
                    focusManager.clearFocus()
                },
            )

            val menuItems by mainActivity.database.menuItemDao().getAll().observeAsState(emptyList())
            var items = if (orderMenuItems) { menuItems.sortedBy { it.title } }
            else { menuItems }

            if (searchPhrase.isNotEmpty()) {
                items = items.filter { it.title.contains(searchPhrase, ignoreCase = true) }
            }

             MenuItemsList(items)

        }//END COL

    }//END Box()

 }//END HomeScreen

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    //verticalAlignment = Alignment.CenterVertically,
                )
                {
                    Column(

                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .background(Color.Green)
                    )
                    {
                        Text(menuItem.title)
                        //Text(menuItem.category)
                        Text(menuItem.description)
                        Text(
                           // modifier = Modifier
                           //     .weight(1f)
                         //       .padding(5.dp),
                         //   textAlign = TextAlign.Right,
                            text = "$%.2f".format(menuItem.price)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(1.0f)
                            .background(Color.Red)
                    )
                    {
                        Text(menuItem.title)
                    }

                }//END Item Row

                Spacer(modifier = Modifier.height(16.dp))
            }//Item Content Lamda

        )//Items loop
    }//END Lazy Column
}//END fun


@Composable
fun searchBox() {
    IconButton(onClick = { })
    {
        Icon(
            imageVector = Icons.Filled.Search,
            modifier = Modifier,
            contentDescription = "Search"
        )
    }
}