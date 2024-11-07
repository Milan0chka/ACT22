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
import com.example.act22.Portfolio
import com.example.act22.SignInPage

@Composable
fun SignUpPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignUpColumn(
                navController,
                authenticationViewModel
            )
        }
    )
}

@Composable
fun SignUpColumn(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var fullName by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Full name", value = fullName, onValueChange = { fullName = it })
    AuthTextField("Company name", value = companyName, onValueChange = { companyName = it })
    AuthTextField("Email", value = email, onValueChange = { email = it })
    AuthTextField("Password", value = password, onValueChange = { password = it }, isPassword = true)

    AuthButton("Sign up") {
        authenticationViewModel.signUpUser(email, password) { success ->
            if (success) {
                navController.navigate(MainPage)
            } else {
                println("fuck off")
            }
        }
    }


    LinkToOtherPage("Already have an account?", "Sign in.", {navController.navigate(SignInPage)})

    OrDevider()

    AuthGoogleButton("Sign up", {})
}

