package com.example.act22.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.Portfolio
import com.example.act22.SignInPage

@Composable
fun SignUpPage(
    navController: NavController
){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignUpColumn(navController)
        }
    )
}

@Composable
fun SignUpColumn(navController: NavController){
    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("First name")
    AuthTextField("Second name")
    AuthTextField("Age")
    AuthTextField("Email")
    AuthTextField("Password", true)
    AuthButton("Sign up", {navController.navigate(Portfolio)})

    LinkToOtherPage("Already have an account?", "Sign in.", {navController.navigate(SignInPage)})

    OrDevider()

    AuthGoogleButton("Sign up", {})
}

