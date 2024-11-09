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
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarMessage


@Composable
fun SignUpPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
) {
    var message: InfoBarMessage? by remember { mutableStateOf(null) }

    AuthPage(
        logoHeight = 100.dp,
        content = {
            SignUpColumn(
                navController,
                authenticationViewModel
            ) { msg ->
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
fun SignUpColumn(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel,
    onShowMessage: (String) -> Unit
) {
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
        authenticationViewModel.signUpUser(email, password) {success, errorMessages ->
            if (success) {
                navController.navigate(Screen.RegistrationSuccess.route)
            } else {
                onShowMessage(errorMessages ?: "An unknown error occurred.")
            }
        }
    }

    LinkToOtherPage("Already have an account?", "Sign in.", { navController.navigate(Screen.SignInPage.route) })

    OrDevider()

    AuthGoogleButton("Sign up", {})
}


