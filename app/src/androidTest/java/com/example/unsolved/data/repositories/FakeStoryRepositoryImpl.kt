package com.example.unsolved.data.repositories

import com.example.unsolved.common.Fixtures
import com.example.unsolved.common.utils.Resource
import com.example.unsolved.domain.model.Story
import com.example.unsolved.domain.repositories.StoryRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeStoryRepositoryImpl : StoryRepository {
    override suspend fun getStory(): Flow<Resource<Story>> = flow {
        val json = Fixtures.getFixtureAsString(this, "story.json")
        val story = Gson().fromJson(json, Story::class.java)
        emit(Resource.Success(story))
    }
}

