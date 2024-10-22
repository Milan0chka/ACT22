package com.example.act22.pages.authentication

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act22.R
import com.example.act22.ui.theme.getLogoResource

@Composable
fun AuthPage(
    logoHeight : Dp,
    isOnBottom: Boolean ?= false,
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isOnBottom == true) Spacer(Modifier.weight(0.5f))
        Box(
            Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, top = 50.dp, end = 50.dp, bottom = 0.dp),
            Alignment.Center
        ) {
            DrawLogo(logoHeight)
        }
        Text(
            text = "Curve",
            style = if (isOnBottom == true) MaterialTheme.typography.displayLarge else MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        if (isOnBottom == true) Spacer(Modifier.weight(0.5f)) else Spacer(Modifier.height(20.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp))
                .background(MaterialTheme.colorScheme.onBackground),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
fun DrawLogo(radius: Dp) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val radius2 = radius / 5 * 4

    val infiniteTransition = rememberInfiniteTransition()

    val offsetY by infiniteTransition.animateFloat(
        initialValue = -1.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    var isHovered by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.2f else 1.0f, // Increase size on hover
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(radius)
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(primaryColor, secondaryColor),
                    center = Offset(size.width / 2, size.height / 2),
                    radius = size.minDimension / 7 * 6
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
                .size(radius2)
                .scale(scale)
                .offset(y = offsetY.dp) // Apply vertical offset animation
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isHovered = true // On press, scale up
                            tryAwaitRelease()
                            isHovered = false // Scale down after release
                        }
                    )
                },
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun AuthButton(prompt: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        shape = RoundedCornerShape(30.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(
            text = prompt,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    string: String,
    isPassword: Boolean = false
) {
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
            containerColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onPrimary
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
fun AuthGoogleButton(string: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
            .border(2.dp, color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(30.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
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
    Row  {
        Text(
            text = "$prompt ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surface,
        )
        Text(
            text = prompt2,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
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
            color = MaterialTheme.colorScheme.onPrimary
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

