package com.example.unsolved.data.repositories

import com.example.unsolved.common.Fixtures
import com.example.unsolved.common.utils.Resource
import com.example.unsolved.data.local.StoryDao
import com.example.unsolved.data.remote.StoryApi
import com.example.unsolved.data.remote.dtos.ApiResponse
import com.example.unsolved.data.remote.dtos.ApiResponseStatus
import com.example.unsolved.data.remote.dtos.StoryDto
import com.google.gson.Gson
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.HttpException
import java.io.IOException

@ExtendWith(MockKExtension::class )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class StoryRepositoryImplTest {
    @MockK
    lateinit var mockStoryApi: StoryApi

    @MockK
    lateinit var mockHttpException: HttpException

    @MockK(relaxUnitFun = true)
    lateinit var mockStoryDao: StoryDao

    lateinit var tStoryDto: StoryDto

    lateinit var repository: StoryRepositoryImpl

    @BeforeAll
    fun setUp() {
        val json = Fixtures.getFixtureAsString(this, "story.json")
        tStoryDto = Gson().fromJson(json, StoryDto::class.java)
    }

    @Nested
    @DisplayName("Device does not have internet")
    inner class WithInternet {
        @BeforeEach
        fun setUp()  {
            coEvery { mockStoryApi.getStory() } throws IOException()

            coEvery { mockStoryDao.getStory() } returns tStoryDto.toStoryEntity()


            repository = StoryRepositoryImpl(storyApi = mockStoryApi, storyDao = mockStoryDao)
        }

        @Test
        fun `getStory() - when storyApi throws IOException, then does not cache story to database`() =
            runBlocking {
                repository.getStory().last()

                coVerify(exactly = 0) { mockStoryDao.deleteAll() }
                coVerify(exactly = 0) { mockStoryDao.insertAll() }
            }

        @Test
        fun `getStory() - when storyApi throws IOException, then return a Failure resource`() =
            runBlocking {
                val result = repository.getStory().last()

                assertTrue(result is Resource.Failure)
            }
    }

    @Nested
    @DisplayName("Device has internet connection")
    inner class NoInternet {
        @BeforeEach
        fun setUp() {
            coEvery { mockStoryApi.getStory() } returns ApiResponse(
                status = ApiResponseStatus(
                    code = 200,
                    message = "hello"
                ), result = tStoryDto
            )

            coEvery { mockStoryDao.getStory() } returns tStoryDto.toStoryEntity()


            repository = StoryRepositoryImpl(storyApi = mockStoryApi, storyDao = mockStoryDao)
        }

        @Test
        fun `getStory() - when storyApi returns success, then cache story to database`() =
            runBlocking {
                repository.getStory().last()

                coVerify { mockStoryDao.deleteAll() }
                coVerify { mockStoryDao.insertAll(tStoryDto.toStoryEntity()) }
            }

        @Test
        fun `getStory() - when storyApi returns success, then emits a Success resource with story data`() =
            runBlocking {
                val result = repository.getStory().last()

                assertTrue(result is Resource.Success)
                assertNotNull(result)
                assertEquals(result.data?.storyId, tStoryDto.story_id)
            }

        @Test
        fun `getStory() - when storyApi throws HttpException, then emits an Failure resource `() = runBlocking {
            coEvery { mockStoryApi.getStory() } throws mockHttpException

            val result = repository.getStory().last()

            assertTrue(result is Resource.Failure)
        }
    }


}