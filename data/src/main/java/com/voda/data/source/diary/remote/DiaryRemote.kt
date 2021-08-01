package com.voda.data.source.diary.remote

import com.voda.domain.entity.Diary
import com.voda.domain.util.Result

interface DiaryRemote {

    suspend fun getDiary(): Result<List<Diary>>
}