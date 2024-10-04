package com.example.act22.pages.start

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.act22.R
import com.example.act22.ui.theme.getLogoResource

@Composable
fun DrawLogo(radius: Dp){
    val primaryColor = MaterialTheme.colorScheme.secondary
    val tetriaryColor = MaterialTheme.colorScheme.tertiary
    val radius2 = radius/5*4

    Box(
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.size(radius)
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(primaryColor, tetriaryColor),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.minDimension /7*6
                ),
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2),
                style = Fill
            )
        }
        Image(
            painter = painterResource(getLogoResource()),
            contentDescription = "Logo",
            modifier = Modifier
                .size(radius2),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun AddButton(string: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(string, style = MaterialTheme.typography.titleSmall)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTextField(string: String, isPassword: Boolean = false) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        placeholder = {
            Text(string, style = MaterialTheme.typography.titleSmall)
        },
        textStyle = MaterialTheme.typography.titleSmall,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None, // Conditionally apply password transformation
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text // Conditionally set keyboard type
        )
    )
}

@Composable
fun AddGoogleButton(string: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, color = MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("$string with", style = MaterialTheme.typography.titleSmall)

            Spacer(modifier = Modifier.width(8.dp)) // Spacing between the logo and text

            Image(
                painter = painterResource(id = R.drawable.google_logo), // Replace with your image resource ID
                contentDescription = "Google Logo",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}

@Composable
fun LinkToOtherPage(prompt:String, prompt2: String, onClick: () -> Unit){
    Row {
        Text(
            text = "$prompt ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            text = prompt2,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}

@Composable
fun OrDevider(){
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier

            .width(300.dp)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

