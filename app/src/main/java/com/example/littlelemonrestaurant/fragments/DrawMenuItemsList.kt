package com.example.littlelemonrestaurant.fragments

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billythelittle.lazycolumns.LazyColumnScrollbarSettings
import com.billythelittle.lazycolumns.LazyColumnWithScrollbar
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemonrestaurant.MainActivity
import com.example.littlelemonrestaurant.R
import com.example.littlelemonrestaurant.savedListState
import com.example.littlelemonrestaurant.ui.theme.DEFAULT_SCREEN_PADDING_HORIZONTAL
import com.example.littlelemonrestaurant.ui.theme.LittleLemonColor

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@OptIn(
    ExperimentalGlideComposeApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun DrawMenuItemsList() {

    val scrollbarSettings = remember {
        mutableStateOf(LazyColumnScrollbarSettings())
    }

    val liveTrigger: Boolean? by savedLiveTrigger.observeAsState()

    val mainActivity = MainActivity.mainActivity
    var listState = rememberLazyListState(
        savedListState.firstVisibleItemIndex,
        savedListState.firstVisibleItemScrollOffset
    )

    var orderMenuItems by rememberSaveable { mutableStateOf(savedOrderMenuItems) }
    var searchPhrase by rememberSaveable { mutableStateOf(savedSearchPhrase) }
    var categorySelected by rememberSaveable { mutableStateOf(savedCategorySelected) }
    if (liveTrigger!!)   //I do this here because if livedata isn't used it wont trigger! ( dumb )
    {
        orderMenuItems = savedOrderMenuItems
        searchPhrase = savedSearchPhrase
        categorySelected = savedCategorySelected
    } else {
        orderMenuItems = savedOrderMenuItems
        searchPhrase = savedSearchPhrase
        categorySelected = savedCategorySelected
    }

    val menuItems by mainActivity.database.menuItemDao().getAll().observeAsState(emptyList())
    var xitems = if (orderMenuItems) {
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


//        LazyColumn(
//            state = listState,
//            modifier = Modifier
//                .fillMaxHeight()
//                .padding(top = 20.dp)
//        )

    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {

        LazyColumnWithScrollbar(
            data = xitems,
            //  state = listState,
            settings = scrollbarSettings.value,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = DEFAULT_SCREEN_PADDING_HORIZONTAL)
        )
        {
            savedListState = listState

            itemsIndexed(
                items = xitems,
                itemContent = { index, menuItem ->

                    if (index == 0) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth(1.0f)
                                .padding(vertical = 10.dp)
                        )
                        {
                            Row(verticalAlignment = Alignment.CenterVertically)
                            {

                                Icon(
                                    painter = painterResource(R.drawable.leaf_left),
                                    contentDescription = null,
                                    modifier = Modifier.height(14.dp),
                                    tint = LittleLemonColor.Black
                                )

                                Text(
                                    text = stringResource(id = R.string.today_specials),
                                    fontWeight = FontWeight(400),
                                    color = LittleLemonColor.Black,
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                    )
                                )

                                Icon(
                                    painter = painterResource(R.drawable.leaf_right),
                                    contentDescription = null,
                                    modifier = Modifier.height(14.dp),
                                    tint = LittleLemonColor.Black
                                )
                            }//END Row

                            DrawMenuDivider()

                        }//END Column
                    }//END index == 0
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        //verticalAlignment = Alignment.CenterVertically,
                    )
                    {

                        Column(verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth(0.6f)

                            ///.background(Color.Green)
                        )
                        {
                            Text(menuItem.title, fontSize = 24.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(menuItem.description, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("$%.2f".format(menuItem.price), fontSize = 18.sp)
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1.0f)
                                .height(IntrinsicSize.Max)
                                .padding(horizontal = 10.dp)
                                .align(alignment = Alignment.CenterVertically)
                            // .background(Color.Blue)
                        )
                        {

                            GlideImage(
                                model = menuItem.image,
                                alignment = Alignment.Center,
                                contentDescription = menuItem.title,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(1.0f)

                                    .aspectRatio(1.0f)
                                    //.fillMaxSize(1.0f)
                                    .clickable(onClick = { })

                                    .shadow(
                                        elevation = 25.dp,
                                        shape = RoundedCornerShape(20.dp),
                                        clip = false,
                                        ambientColor = LittleLemonColor.Black,
                                        spotColor = LittleLemonColor.Black
                                    )
                                    .shadow(
                                        elevation = 15.dp,
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

                    DrawMenuDivider()

                }//Item Content Lamda

            )//Items loop
        }//END Lazy Column

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.dp)
                .height(60.dp)
                .background(
                    Brush.verticalGradient(
//                        0.0f to Color(0xEF400400),
//                        0.3f to Color(0x70400400),
//                        0.7f to Color(0x30808000),
//                        1.0f to Color(0x00808000),
                        0.0f to Color(0xaF101000),
                        0.2f to Color(0x60101000),
                        0.7f to Color(0x10101000),
                        1.0f to Color(0x00101000),
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.dp)
                .height(20.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x10000000),
                            Color(0x00000000),
                        )
                    )
                )
        )
    }//END Box
}//END fun

@Composable
fun DrawMenuDivider() {
    Box(

        modifier = Modifier
            .padding(horizontal = 34.dp, vertical = 8.dp)
            .height(2.dp)
            .fillMaxWidth(1.0f)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(100.0f),
                clip = false,
                ambientColor = Color(0x7100F0F0),
                spotColor = Color(0x7100F0F0)
            )
            .clip(RoundedCornerShape(100.0f))
            .background(Color(0x31000000))

    )
}