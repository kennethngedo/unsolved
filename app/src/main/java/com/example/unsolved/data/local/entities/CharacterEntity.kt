package com.example.unsolved.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsolved.domain.model.Character

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int? = null,
    val character_id: Int,
    val image: MediaEntity,
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