package com.example.act22.pages.main

import com.example.act22.pages.authentication.AuthButton
import com.example.act22.pages.authentication.DrawLogo

import Asset
import Crypto
import TechStock
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.act22.MainPage
import com.example.act22.Portfolio
import com.example.act22.R
import cryptoAssets
import techStocks


@Composable
fun PortfolioBuildingPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        LogoBox()
        InfoBox()
        Spacer(modifier = Modifier.height(15.dp))
        PortfolioBuilder(modifier = Modifier.weight(1f))
        SaveButton(navController)
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
fun SaveButton(navController: NavController) {
    AuthButton("Save", { navController.navigate(Portfolio) })
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Build your portfolio",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            "You can choose up to 10 technology stocks and 3 crypto assets.",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortfolioBuilder(modifier: Modifier) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState
    ) {
        stickyHeader { StickyStockHeader("Tech Stocks") }
        items(techStocks) { stock ->
            AssetCard(stock, {})
        }

        stickyHeader { StickyStockHeader("Cryptos") }
        items(cryptoAssets) { crypto ->
            AssetCard(crypto, {})
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

