package com.example.unsolved.data.remote.dtos

import  com.example.unsolved.domain.model.Character

data class CharacterDto(
    val character_id: Int,
    val image: MediaDto,
    val is_main: Boolean,
    val name: String
) {
    fun toCharacter(): Character {
        return Character(
            characterId = character_id,
            image = image.toMedia(),
            isMain = is_main,
            name = name
        )
    }
}