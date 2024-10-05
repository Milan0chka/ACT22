package com.example.act22.pages.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage
import com.example.act22.Portfolio
import com.example.act22.SignInPage

@Composable
fun CreateSingUp(navController: NavController){
    Column (
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ){
        Box(
            Modifier
                .fillMaxWidth()
                .padding(50.dp),
            Alignment.TopCenter
        ){
            DrawLogo(100.dp)
        }

        Spacer(Modifier.height(20.dp))
        AddSignUpColumn(navController)
    }
}

@Composable
fun AddSignUpColumn(navController: NavController){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp))
            .background(MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(10.dp))

        AddTextField("Full name")
        AddTextField("Email")
        AddTextField("Username")
        AddTextField("Password", true)
        AddButton("Sign up", {navController.navigate(Portfolio)})

        LinkToOtherPage("Already have an account?", "Sign in.", {navController.navigate(SignInPage)})

        OrDevider()

        AddGoogleButton("Sign up", {})

    }
}