package com.voda.data.source.diary.remote

import com.voda.data.api.diary.DiaryApiService
import com.voda.domain.entity.Diary
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.voda.domain.util.Result

class DiaryRemoteDataSource(
//    private val diaryApiService: DiaryApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): DiaryRemote {

    override suspend fun getDiary(): Result<List<Diary>> {
//        diaryApiService.getDiary()
        return Result.Success(emptyList())
    }
}