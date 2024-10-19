package com.kyakubov.swapbarcomposeexample

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kyakubov.swapbarcompose.SelectButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelectButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val fakeItems = listOf(
        LanguageType.UZBEK, LanguageType.ENGLISH
    )

    @Test
    fun selectedShouldBeDisplayed() {

        var selected = LanguageType.UZBEK
        composeTestRule.setContent {
            SelectButton(
                modifier = Modifier,
                options = fakeItems,
                selectedItem = selected
            ) {
                selected = it
            }
        }

        val button = composeTestRule.onNodeWithText(selected.displayName)
        button.performClick()

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(LanguageType.ENGLISH.displayName).performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(selected.displayName).isDisplayed()
        assert(selected == LanguageType.ENGLISH)
    }
}