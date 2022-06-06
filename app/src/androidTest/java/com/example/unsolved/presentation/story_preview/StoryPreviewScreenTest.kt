package com.example.unsolved.presentation.story_preview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.unsolved.common.utils.Constants.TAG_REFRESH_BUTTON
import com.example.unsolved.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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