package com.kyakubov.swapbarcomposeexample

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kyakubov.swapbarcompose.SwapBarComposeView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SwapBarComposeViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeList = LanguageType.entries.toList()

    @Test
    fun testIfItSwipesCorrectly() {
        var selectedItem = fakeList.first()

        composeTestRule.setContent {
            SwapBarComposeView(
                options = fakeList,
                leftOption = fakeList.first(),
                rightOption = fakeList.last(),
                centerButtonModifier = Modifier.testTag("center_button")
            ) {
                selectedItem = it
            }
        }

        val swapButton = composeTestRule.onNodeWithTag("center_button")
        swapButton.performClick()
        assert(selectedItem == fakeList.last())
        composeTestRule.onNodeWithText(selectedItem.displayName).isDisplayed()
    }

}