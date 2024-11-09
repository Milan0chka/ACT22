package com.example.act22.ui.pages.main

import Asset
import Crypto
import TechStock
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.act22.R
import com.example.act22.ui.theme.getLogoResource


@Composable
fun AssetCard(asset: Asset, onClickAction: () -> Unit) {
    val isClicked = remember { mutableStateOf(false) }
    val cardColor = if (isClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondaryContainer
    val cardTextColor = if (isClicked.value) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondaryContainer

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                isClicked.value = !isClicked.value
                onClickAction()
            },
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
            contentColor = cardTextColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp,)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = asset.iconUrl,
                    contentDescription = "${asset.name} logo",
                    placeholder = painterResource(R.drawable.logo_bright),
                    error = painterResource(R.drawable.logo_dark), // Fallback in case of error
                    onLoading = {
                        println("Loading image for ${asset.name}")
                    },
                    onSuccess = {
                        println("Successfully loaded image for ${asset.name}")
                    },
                    onError = {
                        println("Error loading image for ${asset.name}")
                    },
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(asset.name, style = MaterialTheme.typography.bodyLarge)
                Text("Price: $${String.format("%.2f", asset.price)}", style = MaterialTheme.typography.bodyMedium)

                if (asset is TechStock) {
                    Text("Market Cap: ${formatLargeNumber(asset.marketCap)}", style = MaterialTheme.typography.bodyMedium)
                    Text("Sector: ${asset.sector}", style = MaterialTheme.typography.bodyMedium)
                } else if (asset is Crypto) {
                    Text("Blockchain: ${asset.blockchain}", style = MaterialTheme.typography.bodyMedium)
                    Text("Market Dominance: ${asset.marketDominance}%", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}


fun formatLargeNumber(number: Double): String {
    return when {
        number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000) // Billions
        number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000) // Millions
        else -> String.format("%.2f", number) // Less than 1 million, display normally
    }
}

@Composable
fun BasicButton(string: String, onClickAction: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClickAction,
            modifier = Modifier
                .padding(20.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(24.dp)) // Makes the button rounded
                .background(MaterialTheme.colorScheme.tertiary),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary) // Ensures background color is applied correctly
        ) {
            Text(
                text = string,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiary
            )
        }
    }
}


