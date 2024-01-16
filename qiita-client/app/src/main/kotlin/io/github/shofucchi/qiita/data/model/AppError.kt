package io.github.shofucchi.qiita.data.model


fun Exception.toAppError(): AppError = when (this) {
    is java.net.UnknownHostException -> AppError.NetworkError
    is retrofit2.HttpException -> AppError.HttpError
    else -> AppError.UnknownError
}

enum class AppError {
    NetworkError,
    HttpError,
    UnknownError
}