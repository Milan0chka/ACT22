package com.example.act22.ui.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.act22.activity.Screen

@Composable
fun UserPortfolio(
    navController: NavHostController
){
    MainScaffold(navController) {
        if(false){
            EmptyPortfolio(navController)
        } else {
            Spacer(modifier = Modifier.height(30.dp))
            BasicButton("Edit portfolio", { navController.navigate(Screen.PortfolioBuilder.route) })
            AllAssets()
        }
    }
}

@Composable
fun EmptyPortfolio(navController: NavHostController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Your portfolio is empty",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        BasicButton("Build portfolio now", {navController.navigate(Screen.PortfolioBuilder.route)})
    }
}