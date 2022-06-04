package com.example.unsolved.data.remote.dtos

data class ApiResponse<T>(
    val status: ApiResponseStatus,
    val result: T
)

