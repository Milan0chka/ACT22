package com.example.act22.ui.pages.authentication

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
import com.example.act22.activity.Screen
import com.example.act22.viewmodel.AuthenticationViewModel
import com.radusalagean.infobarcompose.InfoBarMessage

@Composable
fun SignInPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var message: InfoBarMessage? by remember { mutableStateOf(null) }

    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignInColumn(
                navController,
                authenticationViewModel
            ){ msg ->
                message = InfoBarMessage(text = msg)
            }
        }
    )

    ErrorNotification(
        message = message,
        onDismiss = { message = null }
    )
}

@Composable
fun SignInColumn(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel,
    onShowMessage: (String) -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Email", value = email, onValueChange = { email = it })
    AuthTextField("Password", value = password, onValueChange = { password = it }, isPassword = true)


    AuthButton("Sign in") {
        authenticationViewModel.signInUser(email, password) { success, errorMessages ->
            if (success) {
                navController.navigate(Screen.MainPage.route)
            } else {
                onShowMessage(errorMessages ?: "An unknown error occurred.")
            }
        }
    }

    LinkToOtherPage(
        prompt = "Do not have account yet?",
        prompt2 = "Sign up.",
        onClick = {navController.navigate(Screen.SignUpPage.route)})

    Spacer(modifier = Modifier.height(10.dp))

    LinkToOtherPage(
        prompt = "Forgot password?",
        prompt2 = "Recover.",
        onClick = {navController.navigate(Screen.PasswordRecovery.route)})

    OrDevider()

    AuthGoogleButton("Sign in", {})
}


