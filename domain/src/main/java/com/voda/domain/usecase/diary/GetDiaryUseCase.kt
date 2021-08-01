package com.voda.domain.usecase.diary

import com.voda.domain.entity.Diary
import com.voda.domain.repository.DiaryRepository
import com.voda.domain.util.Result
import java.lang.Exception

class GetDiaryUseCase(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(
    ): Result<List<Diary>> {
        return try {
            return diaryRepository.getDiary()

        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}