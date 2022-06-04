package com.example.unsolved.data.remote.dtos

import com.example.unsolved.data.local.entities.MediaEntity

data class MediaDto(
    val resource_fid: String,
    val resource_id: Int,
    val resource_preset: String,
    val resource_processed: Boolean,
    val resource_progress: Int,
    val resource_type: String,
    val resource_uri: String
) {
    fun toMediaEntity(): MediaEntity {
        return MediaEntity(
            resource_fid = resource_fid,
            resource_id = resource_id,
            resource_preset = resource_preset,
            resource_processed = resource_processed,
            resource_type = resource_type,
            resource_progress = resource_progress,
            resource_uri = resource_uri
        )
    }
}