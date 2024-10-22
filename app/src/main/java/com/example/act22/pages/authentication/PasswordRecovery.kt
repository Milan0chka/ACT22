package com.example.act22.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.SignInPage


@Composable
fun PassportRecovery(
    navController: NavController
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
    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Email")
    AuthButton("Send email", { navController.navigate(com.example.act22.NewPassword) })

    LinkToOtherPage(
        prompt = "Remembered password?",
        prompt2 = "Sign in.",
        {navController.navigate(SignInPage)}
    )

    Spacer(modifier = Modifier.height(20.dp))
}


