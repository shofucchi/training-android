package io.github.shofucchi.qiita.data.model

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Failure<T>(val error: AppError) : ApiResult<T>()
}