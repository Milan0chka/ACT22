package com.example.act22.ui.pages.authentication

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.activity.Screen


@Composable
fun LandingPage(
    navController: NavController
){
    AuthPage(
        logoHeight = 200.dp,
        isOnBottom = true,
        content = { StartColumn(navController) }
    )
}

@Composable
fun StartColumn(navController: NavController){
    AuthButton ("Sign in", {navController.navigate(Screen.SignInPage.route)})
    AuthButton("Sign up", {navController.navigate(Screen.SignUpPage.route)})
}

