package com.voda.data.api.diary

import com.voda.data.api.diary.dto.response.DiaryResponse
import com.voda.data.api.dto.ApiResult
import retrofit2.http.GET

interface DiaryApiService {

    @GET("")
    suspend fun getDiary(): ApiResult<DiaryResponse>
}