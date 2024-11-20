package com.example.act22.ui.pages.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.activity.Screen
import com.example.act22.viewmodel.AuthenticationViewModel
import com.radusalagean.infobarcompose.InfoBarMessage


@Composable
fun LandingPage(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
){
    var message: InfoBarMessage? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        if (authenticationViewModel.checkIfUserIsLoggedIn()) {
            authenticationViewModel.signOut {
                message = InfoBarMessage(text = "You have been logged out")
            }
        }
    }

    AuthPage(
        logoHeight = 200.dp,
        isOnBottom = true,
        content = { StartColumn(navController) }
    )

    ErrorNotification(
        message = message,
        onDismiss = { message = null },
        padding = 70.dp
    )
}

@Composable
fun StartColumn(navController: NavController){
    AuthButton ("Sign in", {navController.navigate(Screen.SignInPage.route)})
    AuthButton("Sign up", {navController.navigate(Screen.SignUpPage.route)})
}

