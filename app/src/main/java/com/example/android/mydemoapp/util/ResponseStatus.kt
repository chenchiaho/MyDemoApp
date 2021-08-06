package com.example.android.mydemoapp.util


sealed class ResponseStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(message: String): ResponseStatus<T>(message = null)
    class Error<T>(message: String, data: T? = null) : ResponseStatus<T>(data, message)
    class Loading<T>: ResponseStatus<T>()
}