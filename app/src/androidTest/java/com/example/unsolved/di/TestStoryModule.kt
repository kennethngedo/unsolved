package com.example.unsolved.di

import com.example.unsolved.data.repositories.FakeStoryRepositoryImpl
import com.example.unsolved.domain.repositories.StoryRepository
import com.example.unsolved.domain.usecases.GetStory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [StoryModule::class]
)
object TestStoryModule {

    @Provides
    fun providesStoryRepository(): StoryRepository {
        return FakeStoryRepositoryImpl()
    }

    @Provides
    fun provideGetStoryUseCase(repository: StoryRepository) : GetStory {
        return GetStory(repository)
    }

}