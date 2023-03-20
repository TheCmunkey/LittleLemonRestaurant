package com.example.littlelemonrestaurant.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.littlelemonrestaurant.R

@Composable
fun Logo() {

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(id = R.string.logo),
        ///contentScale = ContentScale.Inside,
        modifier = Modifier
            .fillMaxWidth( 0.4f)
            .background(Color.Transparent)
    )
}