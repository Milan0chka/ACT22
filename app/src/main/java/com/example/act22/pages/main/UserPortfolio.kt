package com.example.act22.pages.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

@Composable
fun UserPortfolio(
    navController: NavHostController,
    modifier: Modifier
){
    PageLayout(
        modifier = Modifier.padding(top = 30.dp),
        content = {
            if(false){
                EmptyPortfolio(navController)
            } else {
                BasicButton("Edit portfolio",
                    { navController.navigate(com.example.act22.PortfolioBuilder) })
                AllAssets()
            }
        }
    )
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
        BasicButton("Build portfolio now", {navController.navigate(com.example.act22.PortfolioBuilder)})
    }
}