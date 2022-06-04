package com.example.unsolved.data.remote.dtos

import com.example.unsolved.common.Fixtures
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestInstance

@TestInstance(Lifecycle.PER_CLASS)
class StoryDtoTest {

    private lateinit var tStoryDto: StoryDto

    @BeforeAll
    fun setUp() {
        val storyJson = Fixtures.getFixtureAsString(this, "story.json")
        tStoryDto = Gson().fromJson(storyJson, StoryDto::class.java)
    }

    @Test
    fun `parses json data correctly`() {
        assertEquals(tStoryDto.story_id, 15)
        assertEquals(tStoryDto.publication_status, "published")
        assertEquals(tStoryDto.is_published, true)
    }

    @Test
    fun `toStoryEntity() returns a StoryEntity object`() {
        val story = tStoryDto.toStoryEntity()
        assertNotEquals(story, null)
    }
}