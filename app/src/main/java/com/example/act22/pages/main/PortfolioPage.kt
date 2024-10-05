package com.example.act22.pages.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.MainPage
import com.example.act22.classes.Asset
import com.example.act22.classes.Crypto
import com.example.act22.classes.TechStock
import com.example.act22.pages.start.AddButton
import com.example.act22.pages.start.AddSignUpColumn
import com.example.act22.pages.start.DrawLogo

// Sample tech stocks
val techStocks = listOf(
    TechStock("Apple", 175.50, 2.5E12, "Consumer Electronics"),
    TechStock("Microsoft", 330.10, 2.4E12, "Software"),
    TechStock("Google", 2780.40, 1.8E12, "Search & Advertising"),
    TechStock("Amazon", 3450.15, 1.7E12, "E-commerce"),
    TechStock("Tesla", 900.50, 1.1E12, "Electric Vehicles"),
    TechStock("NVIDIA", 500.60, 0.6E12, "Semiconductors"),
    TechStock("Meta", 320.50, 1.0E12, "Social Media"),
    TechStock("Intel", 58.80, 0.2E12, "Semiconductors"),
    TechStock("AMD", 110.70, 0.15E12, "Semiconductors"),
    TechStock("IBM", 145.30, 0.11E12, "Cloud & AI"),
    TechStock("Cisco", 55.20, 0.2E12, "Networking"),
    TechStock("Oracle", 90.30, 0.3E12, "Enterprise Software"),
    TechStock("Adobe", 630.40, 0.4E12, "Creative Software"),
    TechStock("Salesforce", 240.50, 0.2E12, "CRM Software"),
    TechStock("Spotify", 290.60, 0.05E12, "Music Streaming")
)

// Sample crypto assets
val cryptoAssets = listOf(
    Crypto("Bitcoin", 48000.50, "Bitcoin", 45.0),
    Crypto("Ethereum", 3500.75, "Ethereum", 20.0),
    Crypto("Cardano", 1.25, "Cardano", 4.0),
    Crypto("Binance Coin", 500.90, "Binance Smart Chain", 6.0),
    Crypto("Solana", 150.50, "Solana", 2.5)
)



@Composable
fun OpenPortfolio(navController: NavController) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(50.dp),
            Alignment.TopCenter
        ) {
            DrawLogo(100.dp)
        }

        InfoBox()
        Spacer(modifier = Modifier.height(15.dp))

        PortfolioBuilder(
            modifier = Modifier
                .weight(1f)
        )

        AddButton("Save", {navController.navigate(MainPage)})
    }
}

@Composable
fun InfoBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Build your portfolio",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            "You can choose up to 10 technology stocks and 3 crypto assets.",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PortfolioBuilder(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.secondary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tech Stocks Section
        item {
            Text(
                text = "Tech Stocks",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(15.dp)
            )
        }
        items(techStocks) { stock ->
            AssetCard(stock, {})
        }

        // Crypto Section
        item {
            Text(
                text = "Crypto",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(15.dp)
            )
        }
        items(cryptoAssets) { crypto ->
            AssetCard(crypto, {})
        }
    }
}

@Composable
fun AssetCard(asset: Asset, onClickAction: () -> Unit) {
    // State to track if the card is clicked
    var isClicked = remember { mutableStateOf(false) }

    // Change color based on click state
    val cardColor = if (isClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
    val cardTextColor = if (isClicked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                isClicked.value = !isClicked.value // Toggle the clicked state
                onClickAction() // Perform the provided action
            },
        colors = CardDefaults.cardColors(
            containerColor = cardColor,  // Background color changes on click
            contentColor = cardTextColor,
            disabledContentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = MaterialTheme.colorScheme.errorContainer
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Name of the asset
            Text(asset.name, style = MaterialTheme.typography.bodyLarge)

            // Price with proper formatting (optional)
            Text("Price: $${String.format("%.2f", asset.price)}", style = MaterialTheme.typography.bodyMedium)

            // Conditional rendering based on the asset type
            if (asset is TechStock) {
                Text("Market Cap: ${String.format("%.2f", asset.marketCap)} USD", style = MaterialTheme.typography.bodyMedium)
                Text("Sector: ${asset.sector}", style = MaterialTheme.typography.bodyMedium)
            } else if (asset is Crypto) {
                Text("Blockchain: ${asset.blockchain}", style = MaterialTheme.typography.bodyMedium)
                Text("Market Dominance: ${asset.marketDominance}%", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}




