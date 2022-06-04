package com.example.unsolved.data.remote.dtos

import com.example.unsolved.data.local.entities.CharacterEntity

data class CharacterDto(
    val character_id: Int,
    val image: MediaDto,
    val is_main: Boolean,
    val name: String
) {
    fun toCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            character_id = character_id,
            image = image.toMediaEntity(),
            is_main = is_main,
            name = name
        )
    }
}