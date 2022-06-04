package com.example.unsolved.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.unsolved.domain.model.Media

@Entity
data class MediaEntity(
    @PrimaryKey val id:Int? = null,
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
) {
    fun toMedia(): Media {
        return Media(
            resourceFid = resource_fid,
            resourceId = resource_id,
            resourcePreset = resource_preset,
            resourceProcessed = resource_processed,
            resourceType = resource_type,
            resourceProgress = resource_progress,
            resourceUri = resource_uri
        )
    }
}
