package com.kyakubov.swapbarcompose

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * @param onSwap callback returns the first/left selected object
 */
@Composable
fun <T: Displayable> SwapBarComposeView(
    modifier: Modifier = Modifier,
    options: List<T>,
    leftOption: T,
    rightOption: T,
    centerButtonModifier: Modifier = Modifier,
    centerButtonColors: ButtonColors = ButtonDefaults.buttonColors(),
    sideButtonModifier: Modifier = Modifier,
    sideButtonColors: ButtonColors = ButtonDefaults.buttonColors(),
    onSwap: (T) -> Unit
) {
    val offsetXLeft = remember { Animatable(0f) }
    val offsetXRight = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    var leftLangOption by remember {
        mutableStateOf(leftOption)
    }
    var rightLangOption by remember {
        mutableStateOf(rightOption)
    }
    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        SelectButton(
            modifier = sideButtonModifier.offset(offsetXLeft.value.dp),
            buttonColors = sideButtonColors,
            options = options,
            selectedItem = leftLangOption
        ) { selected ->
            if (selected == rightLangOption) {
                val info = "${rightLangOption.displayName} ➡︎ ${leftLangOption.displayName}"
                Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
                rightLangOption = leftLangOption
            }

            leftLangOption = selected
            onSwap.invoke(leftLangOption)
        }
        RotatingButton(centerButtonModifier, centerButtonColors) {

            scope.launch {
                offsetXLeft.animateTo(
                    targetValue = 200f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                )

                offsetXLeft.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 0,
                        easing = FastOutSlowInEasing
                    )
                )
            }

            scope.launch {
                offsetXRight.animateTo(
                    targetValue = -200f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = FastOutSlowInEasing
                    )
                )

                offsetXRight.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 0,
                        easing = FastOutSlowInEasing
                    )
                )
            }

            val temp = leftLangOption
            leftLangOption = rightLangOption
            rightLangOption = temp
            onSwap.invoke(leftLangOption)
        }
        SelectButton(
            modifier = sideButtonModifier.offset(offsetXRight.value.dp),
            options = options,
            selectedItem = rightLangOption
        ) { selected ->
            if (selected == leftLangOption) {
                val info = "${rightLangOption.displayName} ➡︎ ${leftLangOption.displayName}"
                Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
                leftLangOption = rightLangOption
            }

            rightLangOption = selected
            onSwap.invoke(leftLangOption)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SwapBarComposeViewPreview() {
    Scaffold { innerPadding ->
        SwapBarComposeView(
            modifier = Modifier.padding(innerPadding),
            options = LanguageType.entries.toList(),
            leftOption = LanguageType.ENGLISH,
            rightOption = LanguageType.UZBEK
        ) {
            Log.e("SwapBarCompose", it.displayName)
        }
    }
}

private enum class LanguageType(override var displayName: String): Displayable {
    ENGLISH("English"), UZBEK("Uzbek");
}

