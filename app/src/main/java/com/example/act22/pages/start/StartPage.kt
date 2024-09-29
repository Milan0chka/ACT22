package com.example.act22.pages.start

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.example.act22.SingInPage
import com.example.act22.SingUpPage


@Composable
fun CreateStart(navController: NavController){
    Column (
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ){
        Box(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            Alignment.Center
        ){
            DrawLogo(250.dp)
        }
        AddStartColumn(navController)
    }
}

@Composable
fun AddStartColumn(navController: NavController) {
    Column(
        Modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)) // Apply clipping first
            .background(MaterialTheme.colorScheme.secondary), // Apply background after clipping
        verticalArrangement = Arrangement.Center
    ) {
        AddButton("Sing in", {navController.navigate(SingInPage)})
        AddButton("Sing up", {navController.navigate(SingUpPage)})
    }
}