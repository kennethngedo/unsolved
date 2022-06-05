package com.example.unsolved.presentation.story_preview

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.unsolved.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule

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


//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun when_preview_screen_state_is_success_render_story_correctly() = runTest {
//
//        val deferred = async {
//            delay(50000L)
//        }
//
//        deferred.await()
//        composeTestRule.onNodeWithText(TAG_PREVIEW_BUTTON).assertIsDisplayed()
//        composeTestRule.onNodeWithTag(TAG_PLAY_BUTTON).assertIsDisplayed()
//        composeTestRule.onNodeWithTag(TAG_SUMMARY_TEXT).assertIsDisplayed()
//    }
}