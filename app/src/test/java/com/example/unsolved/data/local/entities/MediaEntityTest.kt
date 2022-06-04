package com.example.unsolved.data.local.entities

import com.example.unsolved.common.Fixtures
import com.example.unsolved.data.remote.dtos.MediaDto
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MediaEntityTest {
    private lateinit var tMediaEntity: MediaEntity

    @BeforeAll
    fun setUp() {
        val mediaJson = Fixtures.getFixtureAsString(this, "media.json")
        tMediaEntity = Gson().fromJson(mediaJson, MediaDto::class.java).toMediaEntity()
    }

    @Test
    fun `toMedia() returns a Media object`() {
        val media = tMediaEntity.toMedia()
        Assertions.assertNotEquals(media, null)
    }
}