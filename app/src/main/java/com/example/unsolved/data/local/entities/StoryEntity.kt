package com.example.unsolved.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsolved.domain.model.Story

@Entity
data class StoryEntity(
    @PrimaryKey val id: Int? = null,
    val age_from: Int,
    val age_to: Int,
    val created: String,
    val duration: String,
    val full_summary: String,
    val genre_id: Int,
    val intro_video_sequence: Int,
    val is_coming_soon: Boolean,
    val is_early_access: Boolean,
    val is_featured: Boolean,
    val is_in_testing: Boolean,
    val is_published: Boolean,
    val language_id: Int,
    val main_character_id: Int,
    val name: String,
    val passcode_clue: String?,
    val passcode_value: String?,
    val price: Int,
    val release_date: String?,
    val release_timezone: String?,
    val short_summary: String,
    val publication_status: String,
    val story_end_sequence: Int,
    val story_id: Int,
    val story_start_sequence: Int,
    val updated: String,
    val list_image: List<MediaEntity>,
    val preview_media: List<MediaEntity>,
    val intro_video: List<MediaEntity>,
    val background_image: List<MediaEntity>,
    val characters: List<CharacterEntity>,
) {
    fun toStory(): Story {
        return Story(
            duration = duration,
            fullSummary = full_summary,
            introVideo = intro_video.map { it.toMedia() },
            mainCharacterId = main_character_id,
            name = name,
            price = price,
            shortSummary = short_summary,
            storyId = story_id,
            listImage = list_image.map { it.toMedia() },
            previewMedia = preview_media.map { it.toMedia() },
            characters = characters.map { it.toCharacter() },
        )
    }
}