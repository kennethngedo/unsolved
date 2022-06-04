package com.example.unsolved.data.local.entities

import com.example.unsolved.common.Fixtures
import com.example.unsolved.data.remote.dtos.StoryDto
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StoryDtoTest {

    private lateinit var tStoryDtoEntity: StoryEntity

    @BeforeAll
    fun setUp() {
        val storyJson = Fixtures.getFixtureAsString(this, "story.json")
        tStoryDtoEntity = Gson().fromJson(storyJson, StoryDto::class.java).toStoryEntity()
    }

    @Test
    fun `toStory() returns a Story object`() {
        val story = tStoryDtoEntity.toStory()
        Assertions.assertNotEquals(story, null)
    }
}