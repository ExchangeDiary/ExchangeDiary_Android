package com.voda.data.api.diary.dto.response

import com.google.gson.annotations.SerializedName

data class DiaryResponse(
    @SerializedName("uid") val uid: String
)
