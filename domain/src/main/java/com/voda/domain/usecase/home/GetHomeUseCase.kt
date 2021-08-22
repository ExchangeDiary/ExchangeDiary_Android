package com.voda.domain.usecase.home

import com.voda.domain.repository.DiaryRepository
import com.voda.domain.usecase.home.dto.Home
import com.voda.domain.util.Result
import com.voda.domain.util.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception

class GetHomeUseCase(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(
    ): Result<Home> = withContext(Dispatchers.IO){
        return@withContext try {

            val diariesByDDayDeferred = async {
                try {
                    //TODO interface 필요
                    diaryRepository.getDiary().getValue()
                } catch (e: Exception){
                    e.printStackTrace()
                    null
                }
            }

            val joinedDiariesDeferred = async {
                try {
                    //TODO interface 필요
                    diaryRepository.getDiary().getValue()
                } catch (e: Exception){
                    e.printStackTrace()
                    null
                }
            }

            val result = Home(
                diariesByDDayDeferred.await(),
                joinedDiariesDeferred.await()
            )

            Result.Success(result)

        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}