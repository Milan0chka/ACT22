package com.example.act22.ui.pages.main

import Asset
import com.example.act22.ui.pages.authentication.DrawLogo

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.activity.Screen
import com.example.act22.ui.pages.authentication.ErrorNotification
import com.example.act22.viewmodel.PortfolioViewModel
import com.radusalagean.infobarcompose.InfoBarMessage
import cryptoAssets
import techStocks


@Composable
fun PortfolioBuildingPage(
    navController: NavController,
    portfolioViewModel: PortfolioViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        LogoBox()
        InfoBox()
        PortfolioBuilder(
            modifier = Modifier.weight(1f),
            portfolioViewModel = portfolioViewModel
        )
        BigButton(
            prompt = "Save",
            onClick = {navController.navigate(Screen.Portfolio.route)}
        )
    }
}

@Composable
fun LogoBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        DrawLogo(75.dp)
    }
}

@Composable
fun InfoBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .shadow(
                elevation = 100.dp,
                shape = RoundedCornerShape(5.dp),
                ambientColor = MaterialTheme.colorScheme.tertiary,
                spotColor = MaterialTheme.colorScheme.secondary
            )
            .padding(bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Build your portfolio",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "You can choose up to 10 technology stocks and 3 crypto assets.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioBuilder(
    modifier: Modifier,
    portfolioViewModel: PortfolioViewModel
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        stickyHeader { StickyStockHeader("Tech Stocks") }
        items(items = techStocks, key = { it.name }) { stock ->
            ToggleAssetCard(
                asset = stock,
                portfolioViewModel = portfolioViewModel
            )
        }

        stickyHeader { StickyStockHeader("Cryptos") }
        items(items = cryptoAssets, key = { it.name }) { crypto ->
            ToggleAssetCard(
                asset = crypto,
                portfolioViewModel = portfolioViewModel
            )
        }
    }

}

@Composable
fun StickyStockHeader(title: String){
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .padding(15.dp)
    )
}

@Composable
fun ToggleAssetCard(
    asset: Asset,
    portfolioViewModel: PortfolioViewModel
) {
    val isClicked = remember { mutableStateOf(portfolioViewModel.isAssetInPortfolio(asset)) }

    val cardColor = if (isClicked.value) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface
    val cardTextColor = if (isClicked.value) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.onSurface

    var message: InfoBarMessage? by remember { mutableStateOf(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                portfolioViewModel.toggleAsset(asset) { msg ->
                    message = InfoBarMessage(text = msg)
                    isClicked.value = !isClicked.value
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = cardTextColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        AssetCardContent(asset)
    }

    // Display error message if any
    ErrorNotification(
        message = message,
        onDismiss = { message = null },
        padding = 0.dp
    )
}
