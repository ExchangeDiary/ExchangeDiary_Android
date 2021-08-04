package com.voda.domain.usecase.user

import com.voda.domain.entity.User
import com.voda.domain.util.Result
import java.lang.Exception

class GetUserUseCase() {

    suspend operator fun invoke(
    ): Result<List<User>> {
        return try {
            return Result.Success(emptyList())

        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}