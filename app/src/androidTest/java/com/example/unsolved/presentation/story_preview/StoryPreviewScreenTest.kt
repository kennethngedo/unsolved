package com.example.unsolved.presentation.story_preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.unsolved.common.theme.UnsolvedTheme
import com.example.unsolved.common.utils.Constants.TAG_PREVIEW_BUTTON
import com.example.unsolved.common.utils.Constants.TAG_REFRESH_BUTTON
import com.example.unsolved.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class StoryPreviewScreenTest {


    @get:Rule(order = 1)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
     var composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
     fun setUp() {
        hiltAndroidRule.inject()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun when_preview_screen_state_is_success_render_story_correctly(): Unit = runBlocking  {
        composeTestRule.onNodeWithTag(TAG_REFRESH_BUTTON).assertIsDisplayed()
    }
}