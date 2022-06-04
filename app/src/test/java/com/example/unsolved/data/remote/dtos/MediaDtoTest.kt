package com.example.unsolved.data.remote.dtos

import com.example.unsolved.common.Fixtures
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestInstance

@TestInstance(Lifecycle.PER_CLASS)
class MediaDtoTest {
    private lateinit var tMediaDto: MediaDto

    @BeforeAll
    fun setUp() {
        val mediaJson = Fixtures.getFixtureAsString(this, "media.json")
        tMediaDto = Gson().fromJson(mediaJson, MediaDto::class.java)
    }

    @Test
    fun `parser json data correctly`(){
        assertEquals(tMediaDto.resource_fid, "9e980267ac53246a65cefda96d33491e379ee8fddd46e6262c4fee7ae9e826a4")
        assertEquals(tMediaDto.resource_id, 428)
        assertEquals(tMediaDto.resource_preset, "image_square_1080")
    }

    @Test
    fun `toMediaEntity() returns a MediaEntity object`() {
        val media = tMediaDto.toMediaEntity()
        assertNotEquals(media, null)
    }
}