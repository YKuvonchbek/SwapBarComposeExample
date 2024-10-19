package com.kyakubov.swapbarcomposeexample

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kyakubov.swapbarcompose.RotatingButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RotatingButtonKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIsRotated() {
        var clicked = false
        composeTestRule.setContent {
            RotatingButton(modifier = Modifier.testTag("rotating_button")) {
                clicked = true
            }
        }

        val button = composeTestRule.onNodeWithTag("rotating_button")
        button.performClick()
        composeTestRule.waitForIdle()
        assert(clicked)
        clicked = false

        button.performClick()
        composeTestRule.waitForIdle()

        assert(clicked)
    }
}