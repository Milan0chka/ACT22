package com.example.act22.ui.pages.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.act22.activity.Screen

@Composable
fun Settings(
    navController: NavController
){
    MainScaffold(navController) {
        EmptyPage(
            text = "Settings are empty",
            buttonText = "Back to main page",
            onClick = {navController.navigate(Screen.MainPage.route)}
        )
    }
}