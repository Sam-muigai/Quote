package com.samkt.quotes.util

sealed class ApiResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ApiResult<T>(data)

    class Error<T>(message: String?) : ApiResult<T>(message = message)
}
