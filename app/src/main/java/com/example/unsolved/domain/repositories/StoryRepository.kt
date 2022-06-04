package com.example.unsolved.domain.repositories

import com.example.unsolved.common.utils.Resource
import com.example.unsolved.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    suspend fun getStory(): Flow<Resource<Story>>
}