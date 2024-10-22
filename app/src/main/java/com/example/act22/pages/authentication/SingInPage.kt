package com.example.act22.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage
import com.example.act22.Recovery
import com.example.act22.SignUpPage

@Composable
fun SignInPage(navController: NavController){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignInColumn(navController)
        }
    )
}

@Composable
fun SignInColumn(navController: NavController){
    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Username")
    AuthTextField("Password", true)
    AuthButton("Sign in", { navController.navigate(MainPage) })

    LinkToOtherPage(
        prompt = "Do not have account yet?",
        prompt2 = "Sign up.",
        onClick = {navController.navigate(SignUpPage)})

    Spacer(modifier = Modifier.height(10.dp))

    LinkToOtherPage(
        prompt = "Forgot password?",
        prompt2 = "Recover.",
        onClick = {navController.navigate(Recovery)})

    OrDevider()

    AuthGoogleButton("Sign in", {})
}


