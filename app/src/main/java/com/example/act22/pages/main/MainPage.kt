package com.example.act22.pages.main

import Asset
import Crypto
import TechStock
import android.text.style.AlignmentSpan
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.credentials.provider.Action
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.act22.R
import cryptoAssets
import techStocks
import kotlin.math.exp


@Composable
fun CreateMainPage(
    navController: NavController,
    modifier: Modifier
){
    PageLayout(
        modifier = Modifier,
        content = {
            CustomSearchBar()
            TypeSort()
            AllAssets()
        }
    )
}


@Composable
fun CustomSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurface)
            .height(95.dp)
            .padding(horizontal = 20.dp)
            .padding(10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "meow",
                tint = MaterialTheme.colorScheme.onSurfaceVariant, // Tint for the icon
                modifier = Modifier.padding(start = 8.dp).clickable {
                },
            )

            CompactTextField("", {}, "meow")
        }
    }
}

@Composable
fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp) // Compact height
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp)) // Custom background and shape
            .padding(horizontal = 8.dp), // Padding around the text field content
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    )
                }
                innerTextField() // Displays the text field input
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun TypeSort() {
    // State to manage dropdown visibility
    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // First Button "Stocks"
        SortButton(
            title = "Stocks",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.45f),
            onClick = {}
        )

        // Second Button "Crypto"
        SortButton(
            title = "Crypto",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.45f),
            onClick = {}
        )

        // Icon with Dropdown
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
                .padding(horizontal = 5.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                .clickable {
                    setExpanded(true) // Open the dropdown when clicked
                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Sort Options",
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            )

            CustomDropDownMenu(expanded, setExpanded)
        }
    }
}

@Composable
fun CustomDropDownMenu(
    expanded: Boolean,
    setExpanded: (Boolean) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        offset = DpOffset(0.dp, 5.dp),
        properties = PopupProperties(focusable = true),
        expanded = expanded,
        onDismissRequest = { setExpanded(false) }
    ) {
        CustomDropDownItem("Cheaper first") {
            // Sort cheaper first logic here
            setExpanded(false)
        }
        CustomDropDownItem("Expensive first") {
            // Sort expensive first logic here
            setExpanded(false)
        }
        CustomDropDownItem("New first") {
            // Sort new first logic here
            setExpanded(false)
        }
    }
}

@Composable
fun CustomDropDownItem(
    title: String,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier = Modifier.fillMaxWidth(),
        text = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.surface
            )
        },
        onClick = onClick
    )
}

@Composable
fun SortButton(
    title: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.padding(horizontal = 5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(7.dp)
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun AllAssets(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = {
            items(techStocks) { stock ->
               AssetCard(stock, {})
            }
            items(cryptoAssets) { crypto ->
                AssetCard(crypto, {})
            }
        }
    )
}

@Composable
fun AssetCard(asset: Asset, onClickAction: () -> Unit) {
    val isClicked = remember { mutableStateOf(false) }
    val cardColor = if (isClicked.value) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.background
    val cardTextColor = if (isClicked.value) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onBackground

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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp) // Ensure size is specified
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
                    contentScale = ContentScale.Fit, // Proper content scale
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(50.dp)) // Circular shape for the image
                        .background(MaterialTheme.colorScheme.background)
                        .border(
                            2.dp,
                            MaterialTheme.colorScheme.secondary,
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





