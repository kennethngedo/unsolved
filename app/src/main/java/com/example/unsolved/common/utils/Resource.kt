package com.example.unsolved.common.utils

sealed class Resource<T>(val message: UIText? = null, val data: T? = null)  {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Failure<T>(message: UIText, data: T?) : Resource<T>(message = message, data = data)
}