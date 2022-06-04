package com.example.unsolved.data.local.entities

import com.example.unsolved.common.Fixtures
import com.example.unsolved.data.remote.dtos.CharacterDto
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CharacterEntityTest {
    private lateinit var tCharacterEntity: CharacterEntity

    @BeforeAll
    fun setUp() {
        val characterJson = Fixtures.getFixtureAsString(this, "character.json")
        val characterDto = Gson().fromJson(characterJson, CharacterDto::class.java)
        tCharacterEntity = characterDto.toCharacterEntity()
    }

    @Test
    fun `toCharacter() returns a Character object`() {
        assertNotEquals(tCharacterEntity.toCharacter(), null)
    }
}