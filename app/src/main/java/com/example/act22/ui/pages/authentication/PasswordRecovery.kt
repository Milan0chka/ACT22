package com.example.act22.ui.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay


@Composable
fun PassportRecovery(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var message: InfoBarMessage? by remember { mutableStateOf(null) }

    AuthPage(
        logoHeight = 100.dp,
        content = {
            RecoveryColumn(
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
fun RecoveryColumn(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel,
    onShowMessage: (String) -> Unit
){
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Email", value = email, onValueChange = { email = it })
    AuthButton("Send email"){
        authenticationViewModel.passwordRecovery(email){ success, errorMessages ->
            if (success) {
                onShowMessage(errorMessages ?: "An unknown error occurred.")
                isEmailSent = true
            }else{
                onShowMessage(errorMessages ?: "An unknown error occurred.")
            }
        }
    }

    LinkToOtherPage(
        prompt = "Remembered password?",
        prompt2 = "Sign in.",
        {navController.navigate(Screen.SignInPage.route)}
    )

    Spacer(modifier = Modifier.height(20.dp))

    LaunchedEffect(isEmailSent) {
        if (isEmailSent) { // Only proceed if email was successfully sent
            delay(5000L)
            navController.navigate(Screen.SignInPage.route)
            isEmailSent = false
        }
    }
}


