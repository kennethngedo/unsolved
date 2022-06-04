package com.example.unsolved.domain.model

data class Media(
    val resourceFid: String,
    val resourceId: Int,
    val resourcePreset: String,
    val resourceProcessed: Boolean,
    val resourceProgress: Int,
    val resourceType: String,
    val resourceUri: String
)