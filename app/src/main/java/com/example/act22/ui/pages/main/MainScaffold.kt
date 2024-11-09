package com.example.act22.ui.pages.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.R
import com.example.act22.activity.Screen
import com.example.act22.ui.theme.getLogoResource

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Painter
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 45.dp, bottom = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            content()
        }
    }
}

@Composable
fun CustomTopBar(navController: NavController) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(getLogoResource()),
                    contentDescription = "Logo",
                    modifier = Modifier.size(35.dp),
                    contentScale = ContentScale.Fit
                )
                Text("Curve",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onPrimary)
            }

            Icon(
                painter = painterResource(R.drawable.icon_logout),
                contentDescription = "Exit",
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(25.dp)
                    .clickable {
                    navController.navigate(Screen.StartPage.route)
                }
                )
        }
    }
}


// Bottom bar navigation with icons
@Composable
fun CustomBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            title = "All assets",
            route = Screen.MainPage.route,
            icon = painterResource(R.drawable.icon_chart)
        ),
        BottomNavItem(
            title = "User Portfolio",
            route = Screen.Portfolio.route,
            icon = painterResource(R.drawable.icon_donut)
        ),
        BottomNavItem(
            title = "Account",
            route = Screen.MainPage.route, //TODO
            icon = painterResource(R.drawable.icon_person)
        ),
        BottomNavItem(
            title = "Settings",
            route = Screen.MainPage.route, //TODO
            icon = painterResource(R.drawable.icon_settings)
        )
    )

    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    )
                )
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Creates clickable navigation items
            items.forEach { item ->
                CustomNavItem(
                    icon = item.icon,
                    label = item.title,
                    onClick = {

                        navController.navigate(item.route)
                    }
                )
            }
        }
    }
}

// Defines each navigation item with an icon and label
@Composable
fun CustomNavItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit
) {
    Icon(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(32.dp))
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = MaterialTheme.colorScheme.secondary,
                    bounded = true
                )
            )
            .padding(8.dp),
        painter = icon,
        contentDescription = label,
        tint = MaterialTheme.colorScheme.onPrimary
    )
}