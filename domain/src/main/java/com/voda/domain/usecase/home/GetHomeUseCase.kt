package com.voda.domain.usecase.home

import com.voda.domain.entity.Diary
import com.voda.domain.entity.User
import com.voda.domain.repository.DiaryRepository
import com.voda.domain.usecase.home.dto.Home
import com.voda.domain.util.Result
import com.voda.domain.util.getValue
import com.voda.domain.vo.HashTag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.*

class GetHomeUseCase(
    private val diaryRepository: DiaryRepository
) {

    suspend operator fun invoke(
    ): Result<Home> = withContext(Dispatchers.IO){
        return@withContext try {

            val diariesByDDayDeferred = async {
                try {
                    //TODO interface 필요
                    getMockData() //diaryRepository.getDiary().getValue()
                } catch (e: Exception){
                    e.printStackTrace()
                    null
                }
            }

            val joinedDiariesDeferred = async {
                try {
                    //TODO interface 필요
                    getMockData() //diaryRepository.getDiary().getValue()
                } catch (e: Exception){
                    e.printStackTrace()
                    null
                }
            }

            val result = Home(
                User("1", "고영희", ""),
                diariesByDDayDeferred.await(),
                joinedDiariesDeferred.await()
            )

            Result.Success(result)

        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    private fun getMockData(): List<Diary> {
        return listOf(
            Diary(
                id = "1",
                title = "주린이는 오늘도 뚠뚠",
                owner = 1,
                bgColor = "",
                bgUrl = "",
                members = listOf(
                    User(id = "1", name = "eunsong", profileImageUrl = ""),
                    User(id = "2", name = "eunsong1", profileImageUrl = ""),
                    User(id = "3", name = "eunsong2", profileImageUrl = ""),
                    User(id = "4", name = "eunsong3", profileImageUrl = ""),
                    User(id = "5", name = "eunsong4", profileImageUrl = ""),
                    User(id = "6", name = "eunsong5", profileImageUrl = ""),
                ),
                interval = Date(),
                invitationUrl = "",
                turnCount = 3,
                hashTags = listOf(
                    HashTag("1", "#떡상가즈아"),
                    HashTag("2", "#집사일기")
                )
            ),

            Diary(
                id = "2",
                title = "고영희 미만 다꾸러!",
                owner = 1,
                bgColor = "",
                bgUrl = "",
                members = listOf(
                    User(id = "1", name = "eunsong", profileImageUrl = ""),
                    User(id = "2", name = "eunsong1", profileImageUrl = ""),
                    User(id = "3", name = "eunsong2", profileImageUrl = ""),
                    User(id = "4", name = "eunsong3", profileImageUrl = ""),
                    User(id = "5", name = "eunsong4", profileImageUrl = ""),
                    User(id = "6", name = "eunsong5", profileImageUrl = ""),
                ),
                interval = Date(),
                invitationUrl = "",
                turnCount = 5,
                hashTags = listOf(
                    HashTag("2", "#집사일기"),
                    HashTag("1", "#떡상가즈아")
                )
            ),

            Diary(
                id = "3",
                title = "고영희 미만 다꾸러!",
                owner = 1,
                bgColor = "",
                bgUrl = "",
                members = listOf(
                    User(id = "1", name = "eunsong", profileImageUrl = ""),
                    User(id = "2", name = "eunsong1", profileImageUrl = ""),
                    User(id = "3", name = "eunsong2", profileImageUrl = ""),
                    User(id = "4", name = "eunsong3", profileImageUrl = ""),
                    User(id = "5", name = "eunsong4", profileImageUrl = ""),
                    User(id = "6", name = "eunsong5", profileImageUrl = ""),
                ),
                interval = Date(),
                invitationUrl = "",
                turnCount = 5,
                hashTags = listOf(
                    HashTag("2", "#집사일기"),
                    HashTag("1", "#떡상가즈아")
                )
            ),
            Diary(
                id = "4",
                title = "고영희 미만 다꾸러!",
                owner = 1,
                bgColor = "",
                bgUrl = "",
                members = listOf(
                    User(id = "1", name = "eunsong", profileImageUrl = ""),
                    User(id = "2", name = "eunsong1", profileImageUrl = ""),
                    User(id = "3", name = "eunsong2", profileImageUrl = ""),
                    User(id = "4", name = "eunsong3", profileImageUrl = ""),
                    User(id = "5", name = "eunsong4", profileImageUrl = ""),
                    User(id = "6", name = "eunsong5", profileImageUrl = ""),
                ),
                interval = Date(),
                invitationUrl = "",
                turnCount = 5,
                hashTags = listOf(
                    HashTag("2", "#집사일기"),
                    HashTag("1", "#떡상가즈아")
                )
            ),
            Diary(
                id = "5",
                title = "고영희 미만 다꾸러!",
                owner = 1,
                bgColor = "",
                bgUrl = "",
                members = listOf(
                    User(id = "1", name = "eunsong", profileImageUrl = ""),
                    User(id = "2", name = "eunsong1", profileImageUrl = ""),
                    User(id = "3", name = "eunsong2", profileImageUrl = ""),
                    User(id = "4", name = "eunsong3", profileImageUrl = ""),
                    User(id = "5", name = "eunsong4", profileImageUrl = ""),
                    User(id = "6", name = "eunsong5", profileImageUrl = ""),
                ),
                interval = Date(),
                invitationUrl = "",
                turnCount = 5,
                hashTags = listOf(
                    HashTag("2", "#집사일기"),
                    HashTag("1", "#떡상가즈아")
                )
            )
        )
    }
}