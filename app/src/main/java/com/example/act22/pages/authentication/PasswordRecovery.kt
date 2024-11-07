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
import com.example.act22.SignInPage


@Composable
fun PassportRecovery(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            RecoveryColumn(navController)
        }
    )
}

@Composable
fun RecoveryColumn( navController: NavController){
    var email by remember { mutableStateOf("") }
    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Email", value = email, onValueChange = { email = it })
    AuthButton("Send email", { navController.navigate(com.example.act22.NewPassword) })

    LinkToOtherPage(
        prompt = "Remembered password?",
        prompt2 = "Sign in.",
        {navController.navigate(SignInPage)}
    )

    Spacer(modifier = Modifier.height(20.dp))
}


