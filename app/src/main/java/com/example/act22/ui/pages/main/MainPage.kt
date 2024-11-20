package com.example.act22.ui.pages.main

import Asset
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.act22.activity.Screen
import cryptoAssets
import techStocks


@Composable
fun CreateMainPage(
    navController: NavController
){
    MainScaffold(navController) {
        CustomSearchBar()
        TypeSort()
        AllAssets(navController)
    }
}


@Composable
fun CustomSearchBar(onSearch: (String) -> Unit = {}) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
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
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onSearch(searchQuery) }
            )

            CompactTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "Search...",
                onDone = { onSearch(searchQuery) }
            )
        }
    }
}

@Composable
fun CompactTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onDone: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                innerTextField()
            },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Composable
fun TypeSort() {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 24.dp)
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        SortButton(
            title = "Stocks",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.45f),
            onClick = {} //todo
        )

        SortButton(
            title = "Crypto",
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.45f),
            onClick = {} //todo
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
                .padding(horizontal = 5.dp)
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(10.dp))
                .clickable { setExpanded(true) }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Sort Options",
                tint = MaterialTheme.colorScheme.onSecondary,
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
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clip(RoundedCornerShape(10.dp)),
        offset = DpOffset(0.dp, 5.dp),
        properties = PopupProperties(focusable = true),
        expanded = expanded,
        onDismissRequest = { setExpanded(false) }
    ) {
        CustomDropDownItem("Cheaper first") { setExpanded(false) }
        CustomDropDownItem("Expensive first") { setExpanded(false) }
        CustomDropDownItem("New first") { setExpanded(false) }
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
                color = MaterialTheme.colorScheme.onSecondaryContainer
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
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(title, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun AllAssets(
    navController: NavController
){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = {
            items(techStocks) { stock ->
               MainAssetCard(navController,stock)
            }
            items(cryptoAssets) { crypto ->
                MainAssetCard(navController, crypto)
            }
        }
    )
}

@Composable
fun MainAssetCard(
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
        AssetCardContent(asset)
    }
}






