package io.github.shofucchi.bmi.ui.test

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.shofucchi.bmi.ui.BmiScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BmiScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bmiScreen_bothTextFieldsAreEmpty() {
        startApp()
        composeTestRule.onNodeWithText("Calculate").assertIsNotEnabled()
    }

    @Test
    fun bmiScreen_heightWasRemoved() {
        startApp()
        composeTestRule.onNodeWithText("Enter your height: cm").performTextInput("170.0")
        composeTestRule.onNodeWithText("Enter your weight: kg").performTextInput("60.0")
        composeTestRule.onNodeWithText("Enter your height: cm").performTextClearance()
        composeTestRule.onNodeWithText("Calculate").assertIsNotEnabled()
    }

    @Test
    fun bmiScreen_weightWasRemoved() {
        startApp()
        composeTestRule.onNodeWithText("Enter your height: cm").performTextInput("170.0")
        composeTestRule.onNodeWithText("Enter your weight: kg").performTextInput("60.0")
        composeTestRule.onNodeWithText("Enter your weight: kg").performTextClearance()
        composeTestRule.onNodeWithText("Calculate").assertIsNotEnabled()
    }

    @Test
    fun bmiScreen_bothTextFieldsAreFilled() {
        startApp()
        composeTestRule.onNodeWithText("Enter your height: cm").performTextInput("170.0")
        composeTestRule.onNodeWithText("Enter your weight: kg").performTextInput("60.0")
        composeTestRule.onNodeWithText("Calculate").assertIsEnabled()
    }

    private fun startApp() {
        composeTestRule.setContent {
            BmiScreen()
        }
    }
}