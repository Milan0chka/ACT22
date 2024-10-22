//package com.example.act22.pages.authentication
//
//import Asset
//import Crypto
//import TechStock
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import coil.compose.AsyncImage
//import com.example.act22.MainPage
//import com.example.act22.R
//import cryptoAssets
//import techStocks
//
//
//@Composable
//fun PortfolioPage(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.background),
//        verticalArrangement = Arrangement.Top
//    ) {
//        LogoBox()
//        InfoBox()
//        Spacer(modifier = Modifier.height(15.dp))
//        PortfolioBuilder(modifier = Modifier.weight(1f))
//        SaveButton(navController)
//    }
//}
//
//@Composable
//fun LogoBox() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(25.dp),
//        contentAlignment = Alignment.TopCenter
//    ) {
//        DrawLogo(75.dp)
//    }
//}
//
//@Composable
//fun SaveButton(navController: NavController) {
//    AuthButton("Save", { navController.navigate(MainPage) })
//}
//
//@Composable
//fun InfoBox() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 50.dp)
//            .shadow(
//                elevation = 100.dp,
//                shape = RoundedCornerShape(5.dp),
//                ambientColor = MaterialTheme.colorScheme.tertiary,
//                spotColor = MaterialTheme.colorScheme.secondary
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            "Build your portfolio",
//            style = MaterialTheme.typography.titleSmall,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//        Text(
//            "You can choose up to 10 technology stocks and 3 crypto assets.",
//            style = MaterialTheme.typography.labelMedium,
//            color = MaterialTheme.colorScheme.onBackground,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun PortfolioBuilder(modifier: Modifier) {
//    val listState = rememberLazyListState()
//    LazyColumn(
//        modifier = modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        state = listState
//    ) {
//        stickyHeader { StickyStockHeader("Tech Stocks") }
//        items(techStocks) { stock ->
//            AssetCard(stock, {})
//        }
//
//        stickyHeader { StickyStockHeader("Cryptos") }
//        items(cryptoAssets) { crypto ->
//            AssetCard(crypto, {})
//        }
//    }
//}
//
//@Composable
//fun StickyStockHeader(title: String){
//    Text(
//        text = title,
//        style = MaterialTheme.typography.titleSmall,
//        color = MaterialTheme.colorScheme.onBackground,
//        textAlign = TextAlign.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                Brush.horizontalGradient(
//                    colors = listOf(
//                        MaterialTheme.colorScheme.secondary,
//                        MaterialTheme.colorScheme.tertiary,
//                        MaterialTheme.colorScheme.tertiary,
//                        MaterialTheme.colorScheme.secondary
//                    )
//                )
//            )
//            .padding(15.dp)
//    )
//}
//
//@Composable
//fun AssetCard(asset: Asset, onClickAction: () -> Unit) {
//    val isClicked = remember { mutableStateOf(false) }
//    val cardColor = if (isClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.background
//    val cardTextColor = if (isClicked.value) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onBackground
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp)
//            .clickable {
//                isClicked.value = !isClicked.value
//                onClickAction()
//            },
//        colors = CardDefaults.cardColors(
//            containerColor = cardColor,
//            contentColor = cardTextColor
//        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(horizontal = 10.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(75.dp) // Ensure size is specified
//                    .padding(2.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                AsyncImage(
//                    model = asset.iconUrl,
//                    contentDescription = "${asset.name} logo",
//                    placeholder = painterResource(R.drawable.logo_bright),
//                    error = painterResource(R.drawable.logo_dark), // Fallback in case of error
//                    onLoading = {
//                        println("Loading image for ${asset.name}")
//                    },
//                    onSuccess = {
//                        println("Successfully loaded image for ${asset.name}")
//                    },
//                    onError = {
//                        println("Error loading image for ${asset.name}")
//                    },
//                    contentScale = ContentScale.Fit, // Proper content scale
//                    modifier = Modifier
//                        .size(70.dp)
//                        .clip(RoundedCornerShape(50.dp)) // Circular shape for the image
//                        .background(MaterialTheme.colorScheme.background)
//                        .border(
//                            2.dp,
//                            MaterialTheme.colorScheme.secondary,
//                            shape = RoundedCornerShape(50.dp)
//                        )
//                        .padding(5.dp)
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(asset.name, style = MaterialTheme.typography.bodyLarge)
//                Text("Price: $${String.format("%.2f", asset.price)}", style = MaterialTheme.typography.bodyMedium)
//
//                if (asset is TechStock) {
//                    Text("Market Cap: ${formatLargeNumber(asset.marketCap)}", style = MaterialTheme.typography.bodyMedium)
//                    Text("Sector: ${asset.sector}", style = MaterialTheme.typography.bodyMedium)
//                } else if (asset is Crypto) {
//                    Text("Blockchain: ${asset.blockchain}", style = MaterialTheme.typography.bodyMedium)
//                    Text("Market Dominance: ${asset.marketDominance}%", style = MaterialTheme.typography.bodyMedium)
//                }
//            }
//        }
//    }
//}
//
//
//fun formatLargeNumber(number: Double): String {
//    return when {
//        number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000) // Billions
//        number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000) // Millions
//        else -> String.format("%.2f", number) // Less than 1 million, display normally
//    }
//}
//
