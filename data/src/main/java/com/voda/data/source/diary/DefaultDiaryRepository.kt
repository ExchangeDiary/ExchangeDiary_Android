package com.voda.data.source.diary

import com.voda.data.source.diary.remote.DiaryRemote
import com.voda.domain.entity.Diary
import com.voda.domain.repository.DiaryRepository
import com.voda.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class DefaultDiaryRepository(
    private val remote: DiaryRemote,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): DiaryRepository {

    override suspend fun getDiary(): Result<List<Diary>> =
        withContext(ioDispatcher) {
            return@withContext try {
                remote.getDiary()
            } catch (e: Exception) {
                Result.Error(e)
            }
    }
}