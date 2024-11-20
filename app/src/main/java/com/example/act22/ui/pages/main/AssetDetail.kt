package com.example.act22.ui.pages.main

import Asset
import Crypto
import TechStock
import android.annotation.SuppressLint
import android.widget.Button
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.act22.activity.Screen
import com.example.act22.viewmodel.PortfolioViewModel
import kotlinx.coroutines.launch

//ALl info about choosen asset
@Composable
fun AssetDetails(
    navHostController: NavHostController,
    portfolioViewModel: PortfolioViewModel,
    assetName: String
){
    val asset = portfolioViewModel.findAssetByName(assetName)

    MainScaffold(navHostController) {
        if (asset == null){
            EmptyPage(
                text = "Asset is not found",
                buttonText = "Back to main page",
                onClick = {navHostController.navigate(Screen.MainPage.route)}
            )
        } else {
            AssetHeader(asset)
            StockChartPlaceholder()
            MoreAboutSection(asset)
            AIpredictions(asset)
            PriceAlerts(asset)
            ButtonRow(navHostController, viewPortfolioViewModel = portfolioViewModel,asset)
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun AssetHeader(asset: Asset) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 40.dp, vertical = 20.dp)
            .padding(top = 35.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = asset.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )

        VerticalDivider(
            modifier = Modifier.height(35.dp),
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )

        Text(
            text = "$${String.format("%.2f", asset.price)}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )

    }
}

@Composable
fun MoreAboutSection(asset: Asset) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "More About",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "More info",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                AssetDetailsContent(asset)
            }
        }
    }
}


@Composable
fun AssetDetailsContent(asset: Asset) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        when (asset) {
            is TechStock -> {
                Text("Market Cap: ${formatLargeNumber(asset.marketCap)}", style = MaterialTheme.typography.bodyMedium)
                Text("Sector: ${asset.sector}", style = MaterialTheme.typography.bodyMedium)
            }
            is Crypto -> {
                Text("Blockchain: ${asset.blockchain}", style = MaterialTheme.typography.bodyMedium)
                Text("Market Dominance: ${asset.marketDominance}%", style = MaterialTheme.typography.bodyMedium)
            }

            else -> {}
        }
    }
}

@Composable
fun ButtonRow(
    navController: NavController,
    viewPortfolioViewModel: PortfolioViewModel,
    asset: Asset
) {
    val isInPortfolio = remember { mutableStateOf(viewPortfolioViewModel.isAssetInPortfolio(asset)) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        if (isInPortfolio.value) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicButton(
                    string = "Buy",
                    onClickAction = {},
                    color = MaterialTheme.colorScheme.tertiary
                )
                BasicButton(
                    string = "Sell",
                    onClickAction = {},
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        } else {
            BasicButton(
                string = "Add to portfolio",
                onClickAction = {
                    viewPortfolioViewModel.toggleAsset(asset) {
                        isInPortfolio.value = !isInPortfolio.value
                    }
                }
            )
        }
    }
}

@Composable
fun AIpredictions(
    asset: Asset
){
    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "AI Predictions",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "More info",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                AssetDetailsContent(asset)
            }
        }
    }
}

@Composable
fun PriceAlerts(
    asset: Asset
){
    var isExpanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "Price alerts",
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "More info",
                    tint = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                AssetDetailsContent(asset)
            }
        }
    }
}

