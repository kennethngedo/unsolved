package com.example.unsolved.presentation.story_preview

import com.example.unsolved.common.Fixtures
import com.example.unsolved.common.utils.Resource
import com.example.unsolved.common.utils.UIEvent
import com.example.unsolved.common.utils.UIText
import com.example.unsolved.data.remote.dtos.StoryDto
import com.example.unsolved.domain.model.Story
import com.example.unsolved.domain.usecases.GetStory
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class StoryPreviewViewModelTest {
    @MockK
    lateinit var mockGetStory: GetStory

    private lateinit var viewModel: StoryPreviewViewModel

    private lateinit var tStory: Story

    @BeforeAll
    fun setUp() {
        val json = Fixtures.getFixtureAsString(this, "story.json")
        tStory = Gson().fromJson(json, StoryDto::class.java).toStoryEntity().toStory()
    }

    @Test
    fun `Given that getStory returns a Success result, when the value of state is accessed, then state should be have a story data`() {
        coEvery { mockGetStory() } returns flow {
            emit(Resource.Success(data = tStory))
        }
        viewModel = StoryPreviewViewModel(mockGetStory)
        viewModel.performGetStory()

        coVerify { mockGetStory() }

        assertEquals(viewModel.state.value.isLoading, false)
        assertNotNull(viewModel.state.value.story)
        assertEquals(viewModel.state.value.story, tStory)
    }

    @Test
    fun `Given that getStory returns a Loading result, when the value of state is accessed, then state is loading value should be true`() {
        coEvery { mockGetStory() } returns flow {
            emit(Resource.Loading(data = null))
        }
        viewModel = StoryPreviewViewModel(mockGetStory)
        viewModel.performGetStory()

        coVerify { mockGetStory() }

        assertEquals(viewModel.state.value.isLoading, true)
    }

    @Test
    fun `Given that getStory returns a Failure result, when the value of state is accessed, then event should emit ShowSnackBar`() = runBlocking {
        val tErrorMessage = "Some sleek error"
        coEvery { mockGetStory() } returns flow {
            emit(Resource.Failure(message = UIText.DynamicString(tErrorMessage), data = null))
        }
        viewModel = StoryPreviewViewModel(mockGetStory)
        viewModel.performGetStory()

        coVerify { mockGetStory() }

        assertEquals(viewModel.state.value.isLoading, false)

        val result = viewModel.event.first()

        assertTrue(result is UIEvent.ShowSnackBar)
    }
}