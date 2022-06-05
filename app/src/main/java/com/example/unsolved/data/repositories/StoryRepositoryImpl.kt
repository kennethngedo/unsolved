package com.example.unsolved.data.repositories

import com.example.unsolved.R
import com.example.unsolved.common.utils.Logger
import com.example.unsolved.common.utils.Resource
import com.example.unsolved.common.exceptions.ApiException
import com.example.unsolved.common.utils.UIText
import com.example.unsolved.data.local.StoryDao
import com.example.unsolved.data.remote.StoryApi
import com.example.unsolved.domain.model.Story
import com.example.unsolved.domain.repositories.StoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class StoryRepositoryImpl @Inject constructor(
    private val storyApi: StoryApi,
    private val storyDao: StoryDao,
    private val logger: Logger? = null
) : StoryRepository {
    private val tag = "StoryRepository"

    override suspend fun getStory(): Flow<Resource<Story>> = flow {
        emit(Resource.Loading())

        var story = storyDao.getStory()?.toStory()
        emit(Resource.Loading(data = story))

        try {
            val storyResponse = storyApi.getStory()
            if (storyResponse.status.code != 0) {
                throw ApiException(msg = storyResponse.status.message)
            }

            val remoteStory = storyResponse.result.toStoryEntity()
            storyDao.deleteAll()
            storyDao.insertAll(remoteStory)

            story = storyDao.getStory()?.toStory()
            emit(Resource.Success(data = story))

        } catch (ex: HttpException) {
            logger?.error(tag, ex.stackTrace.toString())
            emit(
                Resource.Failure(
                    data = story,
                    message = UIText.StringResource(R.string.error_something_went_wrong)
                )
            )

        } catch (ex: IOException) {
            logger?.error(tag, ex.stackTrace.toString())
            emit(
                Resource.Failure(
                    data = story,
                    message = UIText.StringResource(R.string.error_no_internet)
                )
            )
        } catch (ex: ApiException) {
            logger?.error(tag, ex.stackTrace.toString())
            emit(
                Resource.Failure(
                    data = story,
                    message = UIText.DynamicString(ex.msg)
                )
            )
        }


    }
}