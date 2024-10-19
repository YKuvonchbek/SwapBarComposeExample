package com.kyakubov.swapbarcompose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource

@Composable
fun RotatingButton(
    modifier: Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit
) {
    var isRotated by remember { mutableFloatStateOf(0f) }

    val rotationAngle by animateFloatAsState(
        targetValue = isRotated,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    Button(
        modifier = modifier.rotate(rotationAngle),
        colors = buttonColors,
        onClick = {
            isRotated += 180f
            onClick.invoke()
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_swap),
            contentDescription = null
        )
    }
}
