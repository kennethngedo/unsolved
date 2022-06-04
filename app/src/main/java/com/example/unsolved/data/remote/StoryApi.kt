package com.example.unsolved.data.remote

import com.example.unsolved.data.remote.dtos.ApiResponse
import com.example.unsolved.data.remote.dtos.StoryDto
import retrofit2.http.GET

interface StoryApi {

    @GET("/unrd-scratch/resp.json")
    suspend fun getStory(): ApiResponse<StoryDto>
}