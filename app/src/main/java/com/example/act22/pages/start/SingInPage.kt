package com.example.act22.pages.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage


@Composable
fun CreateSingIn(navController: NavController){
    Column (
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(50.dp),
            Alignment.TopCenter
        ){
            DrawLogo(100.dp)
        }

        Spacer(Modifier.height(50.dp))
        AddSignInColumn(navController)


    }
}

@Composable
fun AddSignInColumn(navController: NavController){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)) // Apply clipping first
            .background(MaterialTheme.colorScheme.secondary), // Apply background after clipping
        verticalArrangement = Arrangement.Center
    ){
        AddTextField("Username", false)
        AddTextField("Password", true)
        AddButton("Sign in", {navController.navigate(MainPage)})

    }
}