package com.voda.data.api.dto

import com.google.gson.annotations.SerializedName

data class ApiResult<T>(@SerializedName("results") val results: T?)
