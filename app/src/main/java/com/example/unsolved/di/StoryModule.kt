package com.example.unsolved.di

import android.app.Application
import androidx.room.Room
import com.example.unsolved.common.utils.Constants.BASE_URL
import com.example.unsolved.common.utils.Logger
import com.example.unsolved.common.utils.LoggerImpl
import com.example.unsolved.data.local.StoryDao
import com.example.unsolved.data.local.StoryDatabase
import com.example.unsolved.data.remote.StoryApi
import com.example.unsolved.data.repository.StoryRepositoryImpl
import com.example.unsolved.domain.repositories.StoryRepository
import com.example.unsolved.domain.usecases.GetStory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object StoryModule {

    @Provides
    @Singleton
    fun providesStoryApi() : StoryApi {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(StoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStoryDatabase(app: Application): StoryDatabase {
        return Room.databaseBuilder(
            app, StoryDatabase::class.java, "story_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesStoryDao(db: StoryDatabase): StoryDao = db.dao

    @Provides
    @Singleton
    fun providesLogger(): Logger {
        return LoggerImpl()
    }

    @Provides
    fun providesStoryRepository(
        api: StoryApi,
        dao: StoryDao,
        logger: Logger
    ): StoryRepository {
        return StoryRepositoryImpl(
            storyDao = dao,
            storyApi = api,
            logger = logger
        )
    }

    @Provides
    fun providesGetStoryUseCase(repository: StoryRepository): GetStory {
        return GetStory(repository)
    }




}