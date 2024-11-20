package com.example.act22.ui.pages.main

import Asset
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.activity.Screen
import com.example.act22.viewmodel.PortfolioViewModel

@Composable
fun UserPortfolio(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel = PortfolioViewModel()
) {
    MainScaffold(navController) {
        if (portfolioViewModel.isPortfolioEmpty()) {
            EmptyPage(
                text = "Your portfolio is empty",
                buttonText = "Build portfolio now",
                onClick = { navController.navigate(Screen.PortfolioBuilder.route) }
            )
        } else {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                PortfolioOverview(
                    navController,
                    portfolioViewModel
                )
//                PortfolioAssets(
//                    navController = navController,
//                    portfolioViewModel = portfolioViewModel
//                )
                PortfolioTabs(navController, portfolioViewModel)

            }
        }
    }
}

@Composable
fun PortfolioTabs(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel
) {
    var selectedTab by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(selectedTabIndex = selectedTab) {
            Tab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = { Text("My assets") }
            )
            Tab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = { Text("AI Tips") }
            )
        }

            when (selectedTab) {
                0 -> PortfolioAssets(navController, portfolioViewModel)
                1 -> AITab()
            }

    }
}


@SuppressLint("DefaultLocale")
@Composable
fun PortfolioOverview(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel
){
    val portfolio = portfolioViewModel.getPortfolio()
    val totalPortfolioValue = portfolio.techStocks.sumOf { it.price } + portfolio.cryptos.sumOf { it.price }
    val techStockCount = portfolio.techStocks.size
    val cryptoCount = portfolio.cryptos.size

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Display total portfolio value
            Text(
                text = "Total Value: $${String.format("%.2f", totalPortfolioValue)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display count of stocks and cryptos
            Text(
                text = "Tech Stocks: $techStockCount | Cryptos: $cryptoCount",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp),
            contentAlignment = Alignment.BottomEnd
        ){
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Portfolio",
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(5.dp)
                    .clickable { navController.navigate(Screen.PortfolioBuilder.route) },
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Composable
fun PortfolioAssets(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel
) {
    val portfolio = portfolioViewModel.getPortfolio()
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        portfolio.techStocks.forEach { stock ->
            PortfolioAssetCard(navController, stock)
        }
        portfolio.cryptos.forEach { crypto ->
            PortfolioAssetCard(navController, crypto)
        }
    }
}

@Composable
fun PortfolioAssetCard(
    navController: NavController,
    asset: Asset
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navController.navigate(Screen.AssetDetails.createRoute(asset.name)) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
    ) {
        StockChartPlaceholder(100.dp)
        AssetCardContent(asset)
    }
}

@Composable
fun AITab() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "AI Tips and Predictions",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Coming soon...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}



