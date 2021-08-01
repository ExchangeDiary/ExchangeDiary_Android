package com.voda.domain.repository

import com.voda.domain.entity.Diary
import com.voda.domain.util.Result

interface DiaryRepository {

    suspend fun getDiary(): Result<List<Diary>>

}