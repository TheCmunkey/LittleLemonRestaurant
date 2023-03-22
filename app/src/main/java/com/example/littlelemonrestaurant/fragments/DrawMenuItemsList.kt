package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemonrestaurant.MainActivity
import com.example.littlelemonrestaurant.savedListState
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor
import com.example.littlelemonrestaurant.fragments.savedOrderMenuItems


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DrawMenuItemsList() {

        val liveTrigger: Boolean? by savedLiveTrigger.observeAsState()

        val mainActivity = MainActivity.mainActivity
        var listState = rememberLazyListState(
            savedListState.firstVisibleItemIndex,
            savedListState.firstVisibleItemScrollOffset
        )

        var orderMenuItems by rememberSaveable { mutableStateOf(savedOrderMenuItems) }
        var searchPhrase by rememberSaveable { mutableStateOf(savedSearchPhrase) }
        var categorySelected by rememberSaveable { mutableStateOf(savedCategorySelected) }
        if(liveTrigger!!)   //I do this here because if livedata isn't used it wont trigger! ( dumb )
        {
            orderMenuItems = savedOrderMenuItems
            searchPhrase = savedSearchPhrase
            categorySelected = savedCategorySelected
        }
        else
        {
            orderMenuItems = savedOrderMenuItems
            searchPhrase = savedSearchPhrase
            categorySelected = savedCategorySelected
        }

        val menuItems by mainActivity.database.menuItemDao().getAll().observeAsState(emptyList())
        var xitems = if (orderMenuItems)  {
            menuItems.sortedBy { it.title }
        } else {
            menuItems
        }

        if (searchPhrase.isNotEmpty()) {
            xitems = xitems.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }
        if (categorySelected.isNotEmpty()) {
            xitems = xitems.filter { it.category.contains(categorySelected, ignoreCase = true) }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxHeight()
//                .clickable(
//                    interactionSource = interactionSource,
//                    indication = LocalIndication.current,
//                    onClick = {}
//                )
                .padding(top = 20.dp)
        ) {
            savedListState = listState


            items(
                items = xitems,
                itemContent = { menuItem ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        //verticalAlignment = Alignment.CenterVertically,
                    )
                    {
                        Column(

                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                            ///.background(Color.Green)
                        )
                        {
                            Text(menuItem.title, fontSize = 24.sp)
                            //Text(menuItem.category)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(menuItem.description, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("$%.2f".format(menuItem.price), fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text( menuItem.category, fontSize = 20.sp)

                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1.0f)
                                .padding(horizontal = 10.dp)
                            // .background(Color.Blue)
                        )
                        {

                            GlideImage(
                                model = menuItem.image,
                                contentDescription = menuItem.title,
                                modifier = Modifier
                                    .padding(0.dp)
                                    .fillMaxWidth()

                                    .aspectRatio(1.0f)
                                    //.fillMaxSize(1.0f)
                                    .clickable(onClick = { })

                                    .shadow(
                                        elevation = 20.dp,
                                        shape = RoundedCornerShape(20.dp),
                                        clip = false,
                                        ambientColor = LittleLemonColor.Black,
                                        spotColor = LittleLemonColor.Black
                                    )
                                    .shadow(
                                        elevation = 10.dp,
                                        shape = RoundedCornerShape(20.dp),
                                        clip = false,
                                        ambientColor = LittleLemonColor.Black,
                                        spotColor = LittleLemonColor.Black
                                    )
                                    .shadow(
                                        elevation = 5.dp,
                                        shape = RoundedCornerShape(20.dp),
                                        clip = false,
                                        ambientColor = LittleLemonColor.Black,
                                        spotColor = LittleLemonColor.Black
                                    )
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color.Gray)

                            )

                        }//EBD Column

                    }//END Item Row

                    Spacer(modifier = Modifier.height(16.dp))
                }//Item Content Lamda

            )//Items loop
        }//END Lazy Column
    }//END fun
