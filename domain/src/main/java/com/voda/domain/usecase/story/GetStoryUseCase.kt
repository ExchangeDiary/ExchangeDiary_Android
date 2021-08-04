package com.voda.domain.usecase.story

import com.voda.domain.entity.Story
import com.voda.domain.util.Result
import java.lang.Exception

class GetStoryUseCase() {

    suspend operator fun invoke(
    ): Result<List<Story>> {
        return try {
            return Result.Success(emptyList())

        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}