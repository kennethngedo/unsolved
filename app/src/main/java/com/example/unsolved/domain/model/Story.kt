package com.example.unsolved.domain.model

data class Story(
    val duration: String,
    val fullSummary: String,
    val mainCharacterId: Int,
    val name: String,
    val price: Int,
    val shortSummary: String,
    val storyId: Int,
    val listImage: List<Media>,
    val previewMedia: List<Media>,
    val introVideo: List<Media>,
    val characters: List<Character>,
    val backgroundImage: List<Media>,
)