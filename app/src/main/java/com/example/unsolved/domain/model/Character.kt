package com.example.unsolved.domain.model

data class Character(
    val characterId: Int,
    val image: Media,
    val isMain: Boolean,
    val name: String
)