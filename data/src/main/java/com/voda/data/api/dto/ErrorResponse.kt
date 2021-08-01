package com.voda.data.api.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ErrorResponse(@SerializedName("code") val code: String?,
                         @SerializedName("name") val name: String?,
                         @SerializedName("message") val message: String) : Serializable


