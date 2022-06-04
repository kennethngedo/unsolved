package com.example.unsolved.domain.usecases

import com.example.unsolved.common.utils.Resource
import com.example.unsolved.domain.model.Story
import com.example.unsolved.domain.repositories.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStory @Inject constructor(
    private val repository: StoryRepository
){
   suspend operator fun invoke(): Flow<Resource<Story>> = repository.getStory()
}