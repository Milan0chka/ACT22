package com.example.act22.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage
import com.example.act22.Recovery
import com.example.act22.SignUpPage

@Composable
fun SignInPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignInColumn(
                navController,
                authenticationViewModel
            )
        }
    )
}

@Composable
fun SignInColumn(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(10.dp))

    // Capture user input for email and password
    AuthTextField("Email", value = email, onValueChange = { email = it })
    AuthTextField("Password", value = password, onValueChange = { password = it }, isPassword = true)


    AuthButton("Sign in") {
        authenticationViewModel.signInUser(email, password) { success ->
            if (success) {
                navController.navigate(MainPage)
            } else {
                println("Fuck off loser")
            }
        }
    }

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


