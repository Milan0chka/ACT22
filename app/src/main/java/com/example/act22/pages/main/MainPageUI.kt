package com.example.act22.pages.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.act22.pages.start.DrawLogo
import com.example.act22.ui.theme.getLogoResource

@Composable
fun AddNavBar(){
    Row(
        Modifier
            .height(100.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(getLogoResource()),
                contentDescription = "Logo",
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Fit
            )
            Text("Curve", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        }

        Icon(
            Icons.Rounded.MoreVert,
            contentDescription = "Menu",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(50.dp).clickable {

            })

    }
}