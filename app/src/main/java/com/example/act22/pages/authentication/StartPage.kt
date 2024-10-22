package com.example.act22.pages.authentication

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.SignInPage
import com.example.act22.SignUpPage

@Composable
fun LandingPage(
    navController: NavController
){
    AuthPage(
        logoHeight = 200.dp,
        isOnBottom = true,
        content = { StartColumn(navController)}
    )
}

@Composable
fun StartColumn(navController: NavController){
    AuthButton ("Sing in", {navController.navigate(SignInPage)})
    AuthButton("Sing up", {navController.navigate(SignUpPage)})
}

