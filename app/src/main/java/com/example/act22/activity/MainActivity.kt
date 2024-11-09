package com.example.act22.activity


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.act22.viewmodel.AuthenticationViewModel
import com.example.act22.ui.pages.main.CreateMainPage
import com.example.act22.ui.pages.authentication.LandingPage
import com.example.act22.ui.pages.authentication.PassportRecovery
import com.example.act22.ui.pages.authentication.RegistrationSuccess
import com.example.act22.ui.pages.authentication.SignInPage
import com.example.act22.ui.pages.authentication.SignUpPage
import com.example.act22.ui.pages.main.PortfolioBuildingPage
import com.example.act22.ui.pages.main.UserPortfolio
import com.example.act22.ui.theme.ACT22Theme

sealed class Screen(val route: String) {
    object StartPage : Screen("Start_Page")
    object SignInPage : Screen("SignIn_Page")
    object SignUpPage : Screen("SignUp_Page")
    object MainPage : Screen("Main_Page")
    object Portfolio : Screen("Portfolio")
    object PortfolioBuilder : Screen("PortfolioBuilder")
    object PasswordRecovery : Screen("Recovery")
    object RegistrationSuccess : Screen("RegistrationSuccess")
}


class MainActivity : ComponentActivity() {

    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        val startScreen = if (authViewModel.checkIfUserIsLoggedIn()){ Screen.MainPage.route } else { Screen.StartPage.route }

        enableEdgeToEdge()
        setContent {
            ACT22Theme (){
                NavigationSetUp(
                    startScreen,
                    authViewModel
                )
            }
        }
    }
}

@Composable
fun NavigationSetUp(
    startScreen : String,
    authenticationViewModel: AuthenticationViewModel
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startScreen, builder = {
        composable(Screen.StartPage.route){
            LandingPage(navController)
        }
        composable(Screen.SignInPage.route) {
            SignInPage(
                navController,
                authenticationViewModel
            )
        }
        composable(Screen.SignUpPage.route) {
            SignUpPage(
                navController,
                authenticationViewModel
            )
        }
        composable(Screen.PasswordRecovery.route) {
            PassportRecovery(
                navController,
                authenticationViewModel
            )
        }
        composable(Screen.RegistrationSuccess.route){
            RegistrationSuccess(
                navController,
                authenticationViewModel
            )
        }
        composable(Screen.PortfolioBuilder.route){
            PortfolioBuildingPage(navController)
        }

        composable(Screen.MainPage.route) {
            CreateMainPage(navController)

        }
        composable(Screen.Portfolio.route) {
            UserPortfolio(navController)
        }

    })
}



