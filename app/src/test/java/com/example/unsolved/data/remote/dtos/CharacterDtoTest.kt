package com.example.unsolved.data.remote.dtos

import com.example.unsolved.common.Fixtures
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.TestInstance

@TestInstance(Lifecycle.PER_CLASS)
class CharacterDtoTest {
    lateinit var tCharacterDto: CharacterDto

    @BeforeAll
    fun setUp() {
        val characterJson = Fixtures.getFixtureAsString(this, "character.json")
        tCharacterDto = Gson().fromJson(characterJson, CharacterDto::class.java)
    }

    @Test
    fun `parses json data correctly`(){
        assertEquals(tCharacterDto.character_id, 218 )
        assertEquals(tCharacterDto.is_main, true)
        assertEquals(tCharacterDto.name, "Josie")
    }

    @Test
    fun `toCharacterEntity() returns a CharacterEntity object`() {
        assertNotEquals(tCharacterDto.toCharacter(), null)
    }
}