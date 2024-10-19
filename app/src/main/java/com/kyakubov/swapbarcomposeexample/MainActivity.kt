package com.kyakubov.swapbarcomposeexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kyakubov.swapbarcompose.Displayable
import com.kyakubov.swapbarcompose.SwapBarComposeView
import com.kyakubov.swapbarcomposeexample.ui.theme.SwapBarComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwapBarComposeExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize().padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        SwapBarComposeView(
                            options = LanguageType.entries.toList(),
                            leftOption = LanguageType.ENGLISH,
                            rightOption = LanguageType.UZBEK
                        ) {
                            Log.e("SwapBarCompose", it.displayName)
                        }
                    }
                }
            }
        }
    }
}

enum class LanguageType(override var displayName: String): Displayable {
    ENGLISH("English"), UZBEK("Uzbek");
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwapBarComposeExampleTheme {
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
}