package com.example.act22

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.act22.pages.main.CreateMainPage
//import com.example.act22.pages.authentication.PortfolioPage
import com.example.act22.pages.authentication.LandingPage
import com.example.act22.pages.authentication.NewPassword
import com.example.act22.pages.authentication.PassportRecovery
import com.example.act22.pages.authentication.SignInPage
import com.example.act22.pages.authentication.SignUpPage
import com.example.act22.ui.theme.ACT22Theme
import com.example.act22.ui.theme.getLogoResource

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: Painter
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ACT22Theme (dynamicColor = false){
                var navController = rememberNavController()
                NavHost(navController = navController, startDestination = MainPage, builder = {
                    //No scaffold
                    composable(StartPage){
                        LandingPage(navController)
                    }
                    composable(SignInPage) {
                        SignInPage(navController)
                    }
                    composable(SignUpPage) {
                        SignUpPage(navController)
                    }
                    composable(Portfolio) {
                        //PortfolioPage(navController)
                    }
                    composable(Recovery) {
                        PassportRecovery(navController)
                    }
                    composable(NewPassword) {
                        NewPassword(navController)
                    }

                    composable(MainPage) {
                        MainScaffold(navController) { innerModifier ->
                            CreateMainPage(navController, innerModifier)
                        }
                    }

                })
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffold(
    navController: NavController,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) }
    ) { innerPadding ->
        content(Modifier.padding(innerPadding))
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
                imageVector = Icons.Default.MoreVert,
                contentDescription = "menu",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}


// Bottom bar navigation with icons
@Composable
fun CustomBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            title = "Charts",
            route = MainPage,
            icon = painterResource(R.drawable.chart)
        ),
        BottomNavItem(
            title = "User Chart",
            route = MainPage,
            icon = painterResource(R.drawable.donut)
        ),
        BottomNavItem(
            title = "Account",
            route = MainPage,
            icon = painterResource(R.drawable.person)
        ),
        BottomNavItem(
            title = "Settings",
            route = MainPage,
            icon = painterResource(R.drawable.settings)
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
                    onClick = { navController.navigate(item.route) }
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


