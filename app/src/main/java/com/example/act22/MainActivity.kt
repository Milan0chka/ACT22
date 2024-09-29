package com.example.act22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.act22.pages.main.CreateMainPage
import com.example.act22.pages.start.CreateSingIn
import com.example.act22.pages.start.CreateSingUp
import com.example.act22.pages.start.CreateStart
import com.example.act22.ui.theme.ACT22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACT22Theme (dynamicColor = false){
                var navController = rememberNavController()
                NavHost(navController = navController, startDestination = StartPage, builder = {
                    composable(StartPage){
                        CreateStart(navController)
                    }
                    composable(SingInPage) {
                        CreateSingIn(navController)
                    }
                    composable(SingUpPage) {
                        CreateSingUp(navController)
                    }
                    composable(MainPage) {
                        CreateMainPage(navController)
                    }
                })
            }
        }
    }
}
