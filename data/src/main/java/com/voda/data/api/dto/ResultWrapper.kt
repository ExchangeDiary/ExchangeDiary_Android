package com.voda.data.api.dto

import okhttp3.Headers


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T, val headers: Headers? = null) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse?) :
        ResultWrapper<Nothing>()

    object NetworkError : ResultWrapper<Nothing>()
}

fun Headers.lastModified(): String? {
    return this.get("last-modified")
}