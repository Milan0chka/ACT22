package com.example.act22.pages.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage

@Composable
fun NewPassword( navController: NavController ){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            NewPasswordColumn(navController)
        }
    )
}

@Composable
fun NewPasswordColumn (navController: NavController){
    Spacer(modifier = Modifier.height(10.dp))
    AuthTextField("Enter new password", isPassword = true)
    AuthTextField("Repeat new password", isPassword = true)
    AuthButton("Save", { navController.navigate(MainPage) })
}
