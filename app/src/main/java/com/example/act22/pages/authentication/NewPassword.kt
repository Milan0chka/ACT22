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

@Composable
fun NewPassword(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    AuthPage(
        logoHeight = 100.dp,
        content = {
            NewPasswordColumn(navController)
        }
    )
}

@Composable
fun NewPasswordColumn (navController: NavController){
    var password by remember { mutableStateOf("") }
    var password_repeat by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(10.dp))

    AuthTextField("Enter new password", value = password, onValueChange = { password = it },)
    AuthTextField("Repeat new password", value = password_repeat, onValueChange = { password_repeat = it },)

    Spacer(modifier = Modifier.height(10.dp))

    AuthButton("Save", { navController.navigate(MainPage) })
}
